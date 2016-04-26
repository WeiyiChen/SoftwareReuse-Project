package client.transport;

public class ClientConfigBean {
	public String host;

    public int port;
    
    public int saveCycle;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

	public int getSaveCycle() {
		return saveCycle;
	}

	public void setSaveCycle(int saveCycle) {
		this.saveCycle = saveCycle;
	}
    
    
}
