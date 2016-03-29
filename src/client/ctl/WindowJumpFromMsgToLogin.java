package client.ctl;

import client.intf.IClientWindow;
import client.intf.ILoginWindow;
import client.intf.IMsgWindow;
import client.intf.IWidnowJump;
import client.util.ClientLogger;

public class WindowJumpFromMsgToLogin implements IWidnowJump {

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
		}catch(ClassCastException e1){
			e1.printStackTrace();
		}catch(Exception e2){
			e2.printStackTrace();
		}
		
		return result;
	}

}
