package client.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client11SSender  extends Thread {
	private Socket socket;

	public Client11SSender(Socket socket){
		this.socket = socket;
	}

	public void run() {
		BufferedReader input = null;
		PrintWriter writer = null;
		try {
			input = new BufferedReader(new InputStreamReader(System.in));
			writer = new PrintWriter(socket.getOutputStream());
			String message = null;
			while (true) {
				message = input.readLine();
				// ��������˷�����Ϣ
				writer.println(message);
				writer.flush();
				// ������bye�ͻ����˳�
				if (message.equals("bye")) {
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("clientReciever");
			e.printStackTrace();
		} 
	}
}
