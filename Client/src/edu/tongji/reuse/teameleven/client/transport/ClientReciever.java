package edu.tongji.reuse.teameleven.client.transport;

import edu.tongji.reuse.teameleven.client.intf.IMsgHandle;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientReciever extends Thread {

	private Socket socket;
	private IMsgHandle imh;

	// clientReceiveMsg is used to save message as file
//	private ArrayList<String> clientReceiveMsg = new ArrayList<String>();

	public void exit(){
		this.interrupt();
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
			while (!Thread.currentThread().isInterrupted()) {
				message = reader.readLine();

				if(message == null){
					System.out.println("server closed connection");
					break;
				}
				if(message.equals("bye")){
					break;
				}
                if(imh != null){
                    imh.receiveAndUpdateMsg(message);
                }


			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(!socket.isClosed()){
				try{
					socket.shutdownInput();
					socket.shutdownOutput();
					socket.close();
				}catch(IOException e){
					e.printStackTrace();
				}

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
