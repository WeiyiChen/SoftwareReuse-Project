package edu.tongji.reuse.teameleven.server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

// make the socket handy to send and receive text message
public class SocketWrapper {
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	public SocketWrapper(Socket socket){
		this.socket =  socket;
		try{
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream());
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	public BufferedReader getBufferedReader(){
		return br;
	}
	
	public PrintWriter getPrintWtiter(){
		return pw;
	}
	
	public void sendText(final String strToSend){
        new Thread(new Runnable() {
            @Override
            public void run() {
                pw.println(strToSend);
                pw.flush();
            }
        }).start();
	}
	
	public void quit() throws IOException{
		br.close();
		pw.close();
		socket.close();
	}

	public void sendTexts(final List<String> jsonMsgs) {
		if(jsonMsgs == null){
			return;
		}
		new Thread(new Runnable(){
			@Override
			public void run(){
				for(String jsonMsg : jsonMsgs){
					pw.println(jsonMsg);
				}
				pw.flush();
			}
		}).start();
	}
}
