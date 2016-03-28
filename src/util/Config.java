package util;

public class Config {
	private String ip;
	private int port;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Config(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}
	
	public Config() {
		super();
	}
	@Override
	public String toString() {
		return "Config [ip=" + ip + ", port=" + port + "]";
	}
	
}
