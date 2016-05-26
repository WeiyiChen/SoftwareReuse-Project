package edu.tongji.reuse.teameleven.rls.client.intf;

/**
 * A controller for send message and receive message
 * @author Dai
 *
 */
public interface IMsgHandle {
	
	/**
	 * Send message for a target user. this method has not been used
	 * in current version(2016 03 29)
	 * @param msg - message to send
	 * @param targetUser - target user
	 */
	void sendMessage(Object msg, String targetUser);
	
	/**
	 * Send message for every other user.
	 * @param msg - message to send
	 */
	void sendMessage(Object msg);
	
//	Object getMessage();
	
	/**
	 * Receive message from server
	 * @param msg - message received from server
	 */
	void receiveAndUpdateMsg(Object msg);
	
}
