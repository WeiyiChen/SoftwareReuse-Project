package edu.tongji.reuse.teameleven.client.ctl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import edu.tongji.reuse.teameleven.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.client.transport.ClientSocket;
import edu.tongji.reuse.teameleven.client.transport.StrMsgSender;
import packedEncrypt.EncryptImpl;
import packedEncrypt.IEncrypt;
import edu.tongji.reuse.teameleven.client.intf.ILogInCheck;
import edu.tongji.reuse.teameleven.client.intf.IMsgSender;

/**
 * check if the usr and password is correct
 * @author Dai
 *
 */
public class LogInCheck implements ILogInCheck{
	
	private static boolean result = false;
	private static boolean isReceived = false;
	private static IEncrypt encrypt = new EncryptImpl();
	
	/**
	 * After send login message, create a new thread to receive login result
	 * the longest wait time is 1000ms
	 */
	@Override
	public boolean check(String usrName, String pwd) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		result = false;
		String secretPassword = encrypt.encrypt(pwd);
		String jsonStr = JsonBuilderClient.getPasswordJson(usrName, secretPassword);
		IMsgSender msgSender = new StrMsgSender();
		System.out.println(jsonStr);
		msgSender.send(jsonStr);
		
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					isReceived = false;
					result = receiveLogInResult(ClientSocket.getSocket());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					result = false;
					e.printStackTrace();
				}
			}
			
		});
		t.start();
		try {
			for(int i = 0; i < 100; i++){
				TimeUnit.MILLISECONDS.sleep(10);
				if(isReceived)
					break;
			}
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
		t.stop();
//		isReceived = false;
		return result;
	}
	
	private boolean receiveLogInResult(Socket socket){
		BufferedReader reader = null;
		String msg = null;
//		boolean result = false;
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true){
				msg = reader.readLine();
				if(msg != null){
					isReceived = true;
//					System.out.println("receive login result");
//					System.out.println(msg);
					if(JsonBuilderBase.getLoginSucceedJson().equals(msg)){
						return true;
					}
					else if(JsonBuilderBase.getLoginFailedJson().equals(msg)){
						return false;
					}
					else{
						System.out.println("unknown login result");
						// ignore other kind of message
						continue;
					}
					
//					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
}
