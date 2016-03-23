package client;

import java.io.IOException;

public class Client11Entry {

	@SuppressWarnings("unused")
	static public void main(String[] args) {
		try {
			Client11 client = new Client11();
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		}
	}
}
