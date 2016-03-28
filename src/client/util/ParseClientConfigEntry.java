package client.util;

public class ParseClientConfigEntry {
	public static void main(String[] args){
		ParseClientConfig.writeClientConfigFile();
		ClientConfig config = (ClientConfig) ParseClientConfig.getConfig("ClientConfig.xml");
		System.out.println(config.getIp());
		System.out.println(config.getPort());
	}
}
