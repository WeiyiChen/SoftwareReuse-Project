package edu.tongji.reuse.teameleven.client.intf;
/**
 * An interface encapsulate the behavior to update the window ui with a new massge.
 * @author Dai
 * @version 1.0
 * @since 2016-03-29
 */
public interface IAddMsgToUI {
	/**
	 * 
	 * @param imw message window to update
	 * @param msg message to show
	 * @throws ClassCastException msg(Object) will be cast before being dealt with
	 */
	void addMsg(IMsgWindow imw, Object msg) throws ClassCastException;
}
