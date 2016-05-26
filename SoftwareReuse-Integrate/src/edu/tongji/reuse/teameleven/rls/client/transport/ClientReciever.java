package edu.tongji.reuse.teameleven.rls.client.transport;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import edu.tongji.reuse.teameleven.rls.client.intf.IMsgHandle;

public class ClientReciever extends Thread {
	private static boolean isExit = false;
	private Socket socket;
	private IMsgHandle imh;

	// clientReceiveMsg is used to save message as file
	private ArrayList<String> clientReceiveMsg = new ArrayList<String>();

	public static void exit(){
		isExit = true;
	}
	
	public ClientReciever(Socket socket) {
		this.socket = socket;
	}

	public ClientReciever(Socket socket, IMsgHandle imh){
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
		if(msg == null){
			return;
		}
		String clientSavedMsgPath = "record/client/client_msg.txt";
		File file = new File(clientSavedMsgPath);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
	    FileWriter writer = new FileWriter(clientSavedMsgPath);
        for(String str: msg) {
          writer.write(str);
        }
        writer.close();
	    
	}
}
