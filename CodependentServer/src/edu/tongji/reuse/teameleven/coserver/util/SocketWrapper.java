package edu.tongji.reuse.teameleven.coserver.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        new Thread(()->{
                pw.println(strToSend);
                pw.flush();
        }).start();
    }
	
	public void sendText(final String strToSend, final int waitTime){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    TimeUnit.MILLISECONDS.sleep(waitTime);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                pw.println(strToSend);
                pw.flush();
            }
        }).start();
	}
	
	public void close() throws IOException{
		br.close();
		pw.close();
		if(!socket.isClosed()){
			socket.close();
		}
	}

	public void sendTexts(final List<String> jsonMsgs) {
		sendTexts(jsonMsgs, 1000);
	}

	public void sendTexts(final List<String> jsonMsgs, final int waitTime){
		if(jsonMsgs == null){
			return;
		}
		new Thread(()->{
			try{
				TimeUnit.MILLISECONDS.sleep(waitTime);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			for(String jsonMsg : jsonMsgs){
				pw.println(jsonMsg);
			}
			pw.flush();
		}).start();
	}
}
