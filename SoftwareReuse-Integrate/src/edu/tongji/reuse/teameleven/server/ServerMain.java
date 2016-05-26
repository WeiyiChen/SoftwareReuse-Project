package edu.tongji.reuse.teameleven.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.tongji.reuse.teameleven.server.transport.ServerEntry;


public class ServerMain {

	/**
	 * Description
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) {
		ServerEntry serverEntry = new ServerEntry();
		serverEntry.start();
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				String cmd = input.readLine();
				if (cmd.equals("quit")) {
					serverEntry.safeQuit();
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("wait 3 seconds");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main exit");
		System.exit(0);
	}
	
//	private interface cmdAnalizer{
//		public int runCmd(String cmd);
//	}

}
