package client.transport;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import client.intf.IMsgHandle;

public class Client11SReciever extends Thread {
	private static boolean isExit = false;
	private Socket socket;
	private IMsgHandle imh;
	private ArrayList<String> clientReceiveMsg = new ArrayList<String>();

	public static void exit(){
		isExit = true;
	}
	
	public Client11SReciever(Socket socket) {
		this.socket = socket;
	}

	public Client11SReciever(Socket socket, IMsgHandle imh){
		this.socket = socket;
		this.imh = imh;
	}
	public void run() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String message = null;
			while (true) {
				message = reader.readLine();
				//System.out.println(message);
				clientReceiveMsg.add(message);
				saveClientMsgToFile(clientReceiveMsg);
				if(imh != null){
					imh.receiveAndUpdateMsg(message);
				}
				if(message.equals("bye")){
					break;
				}
				if(isExit){
					isExit = false;
					break;
				}
			}
			
		} catch (IOException e) {
			if (!socket.isClosed()) {
				e.printStackTrace();
			}
		} 
	}
	
	private void saveClientMsgToFile(ArrayList<String> msg) throws IOException {
	    FileWriter writer = new FileWriter("client_msg.txt"); 
        for(String str: msg) {
          writer.write(str);
        }
        writer.close();
	    
	}
}
