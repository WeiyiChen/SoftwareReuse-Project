package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import server.transport.Server11Entry;

public class ServerMain {

	/**
	 * Description
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		Server11Entry server11Entry = new Server11Entry();
		server11Entry.start();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String cmd;
			try {
				cmd = input.readLine();
				if (cmd.equals("quit")) {
					server11Entry.quit();
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("main exit");
	}

}
