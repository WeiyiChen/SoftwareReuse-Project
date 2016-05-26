package edu.tongji.reuse.teameleven.client.intf;

/**
 * The UI for message window
 * @author Dai
 *
 */
public interface IMsgWindow extends IClientWindow{
	
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
	
//	/**
//	 * to make the message window visible
//	 */
//	void toShowWindow();
//	
//	/**
//	 * to close the message window
//	 */
//	void toCloseWindow();
	
	/**
	 * to get a message handler (A controller for send message and receive message)
	 * @return - A controller for send message and receive message
	 */
	IMsgHandle getMsgHandle();

	/**
	 * add a user to contact list
	 * @param user - the user to be added to contact list
     */
	void addContact(String user);

	/**
	 * remove a user from the contact list
	 * @param user - the user to be removed from contact list
	 * @return
     */
	boolean removeContact(String user);
}
