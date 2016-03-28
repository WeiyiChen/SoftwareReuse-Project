package client.util;

public class ClientConfig {
	private String ip = "1.1.1.1";
	private String port = "0000";
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public int getIntPort(){
		return Integer.parseInt(port);
	}
	
	public ClientConfig() {
		super();
	}
	
	public ClientConfig(String ip, String port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	@Override
	public String toString() {
		return "Config [ip=" + ip + ", port=" + port + "]";
	}
	
}
