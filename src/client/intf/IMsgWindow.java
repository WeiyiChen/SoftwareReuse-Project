package client.intf;

public interface IMsgWindow {
	void setUsr(String usr);
	void appendMsgRecord(String singleLineMsg);
	void sendMsg();
	void showMsgWindow();
	void closeMsgWindow();
	IMsgHandle getMsgHandle();
	
}
