package client.ctl;

import client.intf.IClientWindow;
import client.intf.ILoginWindow;
import client.intf.IMsgWindow;
import client.intf.IWindowJump;
import client.transport.Client11SReciever;
import client.util.ClientLogger;

/**
 * jump from message window to login window
 * used when need relogin
 * @author Dai
 *
 */
public class WindowJumpFromMsgToLogin implements IWindowJump {

	@Override
	public boolean jump(IClientWindow from, IClientWindow to) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{
			IMsgWindow imw = (IMsgWindow)from;
			ILoginWindow ilw = (ILoginWindow)to;
			ilw.toShowWindow();
			ilw.setTip("redo login!");
			result = true;
			
			imw.toCloseWindow();
			ClientLogger.setIsLogin(false);
			Client11SReciever.exit();
		}catch(ClassCastException e1){
			e1.printStackTrace();
		}catch(Exception e2){
			e2.printStackTrace();
		}
		
		return result;
	}

}