package edu.tongji.reuse.teameleven.rls.server.ctrl;

public class ServerConfigBean {
	private int maxMessagesPerLogin;
	private int maxMessagesPerSecond;
	private int saveCycle;
	private int port;
	private int zipCycle;
	private int reZipCycle;
	public int getMaxMessagesPerLogin() {
		return maxMessagesPerLogin;
	}
	public void setMaxMessagesPerLogin(int maxMessagesPerLogin) {
		this.maxMessagesPerLogin = maxMessagesPerLogin;
	}
	public int getMaxMessagesPerSecond() {
		return maxMessagesPerSecond;
	}
	public void setMaxMessagesPerSecond(int maxMessagesPerSecond) {
		this.maxMessagesPerSecond = maxMessagesPerSecond;
	}
	public int getSaveCycle() {
		return saveCycle;
	}
	public void setSaveCycle(int saveCycle) {
		this.saveCycle = saveCycle;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getZipCycle() {
		return zipCycle;
	}

	public void setZipCycle(int zipCycle) {
		this.zipCycle = zipCycle;
	}

	public int getReZipCycle() {
		return reZipCycle;
	}

	public void setReZipCycle(int reZipCycle) {
		this.reZipCycle = reZipCycle;
	}
}
