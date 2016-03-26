package server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketPlus {
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	
	public SocketPlus(Socket socket){
		this.socket =  socket;
		try{
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new  PrintWriter(socket.getOutputStream());
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
	
	public void sendText(String strToSend){
		pw.println(strToSend);
		pw.flush();
	}
}
