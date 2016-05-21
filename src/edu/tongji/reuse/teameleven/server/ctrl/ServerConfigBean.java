package edu.tongji.reuse.teameleven.server.ctrl;

public class ServerConfigBean {
	private int maxMessagesPerLogin;
	private int maxMessagesPerSecond;
	private int saveCycle;
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
}
