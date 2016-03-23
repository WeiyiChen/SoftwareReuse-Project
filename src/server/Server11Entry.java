package server;

import java.io.IOException;

public class Server11Entry {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			Server11 server = new Server11();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}

}
