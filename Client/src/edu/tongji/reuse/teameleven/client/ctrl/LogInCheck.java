package edu.tongji.reuse.teameleven.client.ctrl;

import edu.tongji.reuse.teameleven.client.intf.ILogInCheck;
import edu.tongji.reuse.teameleven.client.intf.IMsgSender;
import edu.tongji.reuse.teameleven.client.transport.ClientLoginSocket;
import edu.tongji.reuse.teameleven.client.transport.ClientMsgSocket;
import edu.tongji.reuse.teameleven.client.transport.StrMsgSender;
import edu.tongji.reuse.teameleven.codependent.base.JsonAnalizerBase;
import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.codependent.model.NetInfo;
import edu.tongji.reuse.teameleven.rls.encrypt.message.EncryptImpl;
import edu.tongji.reuse.teameleven.rls.encrypt.message.IEncrypt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * check if the usr and password is correct
 * @author Dai
 *
 */
public class LogInCheck implements ILogInCheck {
	
	private boolean loginResult = false;
    private boolean isReceivedNetInfo = false;
    private boolean isLoopReceive = true;
//	private static boolean isReceived = false;
	private static IEncrypt encrypt = new EncryptImpl();
	
	/**
	 * After send login message, create a new thread to receive login loginResult
	 * the longest wait time is 1000ms
	 */
	@Override
	public boolean check(String usrName, String pwd) throws UnknownHostException, IOException {

		loginResult = false;
		String secretPassword = encrypt.encrypt(pwd);
		String jsonStr = JsonBuilderClient.getPasswordJson(usrName, secretPassword);
		IMsgSender msgSender = new StrMsgSender();
		System.out.println(jsonStr);
		msgSender.send(jsonStr);

		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {

				try {
//					isReceived = false;
					loginResult = receiveLogInResult(ClientLoginSocket.getSocket());

				} catch (IOException e) {

					loginResult = false;
					e.printStackTrace();
				}
			}

		});
		t.start();
		try {
			for(int i = 0; i < 500; i++){
				TimeUnit.MILLISECONDS.sleep(2);
//				if(isReceived)
//					break;
			}

		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
        isLoopReceive = false;
//		t.stop();
//		isReceived = false;
		return loginResult;
	}
	
	private boolean receiveLogInResult(Socket socket){
		BufferedReader reader = null;
		String msg = null;
//		boolean loginResult = false;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(isLoopReceive){
				msg = reader.readLine();
				if(msg != null){
//					isReceived = true;
//					System.out.println("receive login loginResult");
//					System.out.println(msg);
					if(JsonBuilderBase.getLoginSucceedJson().equals(msg)){
						loginResult = true;
					}
					else if(JsonBuilderBase.getLoginFailedJson().equals(msg)){
						return false;
					}
                    else if(JsonAnalizerBase.getMessageType(msg).equals("netinfo")){
                        NetInfo netInfo = new NetInfo();
                        netInfo.setByJsonString(msg);
                        ClientMsgSocket.setIPAndPort(netInfo.getIp(), netInfo.getPort());
                    }
					else{
						System.out.println("unknown login loginResult");
						// ignore other kind of message
						continue;
					}
					
//					break;
				}
                else{
                    return false;
                }
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		return false;
		
	}
	
}
