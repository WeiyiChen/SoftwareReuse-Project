package client.intf;

/**
 * The UI for message window
 * @author Dai
 *
 */
public interface IMsgWindow {
	
	/**
	 * message window can display which user is use the window
	 * @param usr
	 */
	void setUsr(String usr);
	
	/**
	 * to add a line in the message record area
	 * @param singleLineMsg
	 */
	void appendMsgRecord(String singleLineMsg);
	
	/**
	 * to trigger send message in the message window
	 */
	void sendMsg();
	
	/**
	 * to make the message window visible
	 */
	void showMsgWindow();
	
	/**
	 * to close the message window
	 */
	void closeMsgWindow();
	
	/**
	 * to get a message handler (A controller for send message and receive message)
	 * @return
	 */
	IMsgHandle getMsgHandle();
	
}
