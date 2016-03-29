package client.intf;
/**
 * The UI for login window
 * @author Dai
 *
 */
public interface ILoginWindow {
	/**
	 * 
	 * @param tip - set tip for tip area in the window
	 */
	void setTip(String tip);
	
	/**
	 * to make login window visible
	 */
	void showLoginWindow();
	
	/**
	 * to close login window
	 */
	void closeMsgWindow();
	
}
