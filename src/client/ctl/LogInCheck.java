package client.ctl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import client.intf.ILogInCheck;
import client.intf.IMsgSender;
import client.transport.ClientSocket;
import client.transport.JsonMsgSender;

public class LogInCheck implements ILogInCheck{
	
	private static boolean result = false;

	@Override
	public boolean check(String usrName, String pwd) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		result = false;
		String jsonStr = JsonBuilderClient.getPasswordJson(usrName, pwd);
		IMsgSender msgSender = new JsonMsgSender();
		msgSender.send(ClientSocket.getSocket(), jsonStr);
		
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					String resultStr = receiveLogInResult(ClientSocket.getSocket());
					if(resultStr!= null && resultStr.equals(JsonBuilderClient.getLoginSucceedJson())){
						result = true;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					result = false;
					e.printStackTrace();
				}
			}
			
		});
		t.start();
		try {
			for(int i = 0; i < 5; i++){
				TimeUnit.SECONDS.sleep(1);
				if(result)
					break;
			}
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		t.stop();
		return result;
	}
	
	private String receiveLogInResult(Socket socket){
		BufferedReader reader = null;
		String msg = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true){
				msg = reader.readLine();
				if(msg != null){
					return msg;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
