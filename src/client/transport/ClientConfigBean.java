package client.transport;

public class ClientConfigBean {
	public String host;

    public int port;
    
    public int logSaveCycle;
    
    public int beginCompressSeconds;
    
    public int internalCompressSeconds;
    
    public String compressPathPrex;

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

	public int getLogSaveCycle() {
		return logSaveCycle;
	}

	public void setLogSaveCycle(int logSaveCycle) {
		this.logSaveCycle = logSaveCycle;
	}

	public int getBeginCompressSeconds() {
		return beginCompressSeconds;
	}

	public void setBeginCompressSeconds(int beginCompressSeconds) {
		this.beginCompressSeconds = beginCompressSeconds;
	}

	public int getInternalCompressSeconds() {
		return internalCompressSeconds;
	}

	public void setInternalCompressSeconds(int internalCompressSeconds) {
		this.internalCompressSeconds = internalCompressSeconds;
	}

	public String getCompressPathPrex() {
		return compressPathPrex;
	}

	public void setCompressPathPrex(String compressPathPrex) {
		this.compressPathPrex = compressPathPrex;
	}

	
    
    
}
