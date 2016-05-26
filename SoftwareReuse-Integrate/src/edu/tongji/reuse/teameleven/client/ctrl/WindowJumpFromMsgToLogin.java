package edu.tongji.reuse.teameleven.client.ctrl;

import edu.tongji.reuse.teameleven.client.intf.IMsgWindow;
import edu.tongji.reuse.teameleven.client.transport.ClientReciever;
import edu.tongji.reuse.teameleven.client.intf.IClientWindow;
import edu.tongji.reuse.teameleven.client.intf.ILoginWindow;
import edu.tongji.reuse.teameleven.client.intf.IWindowJump;

/**
 * jump from message window to login window
 * used when need relogin
 * @author Dai
 *
 */
public class WindowJumpFromMsgToLogin implements IWindowJump {

	@Override
	public boolean jump(IClientWindow from, IClientWindow to) {

		boolean result = false;
		try{
			IMsgWindow imw = (IMsgWindow)from;
			ILoginWindow ilw = (ILoginWindow)to;
			ilw.toShowWindow();
			ilw.setTip("redo login!");
			result = true;
			
			imw.toCloseWindow();
//			ClientLogger.setIsLogin(false);
//			ClientRecordController crc = ClientRecordController.getInstance();
//			crc.reset();
			ClientMonitorController.reset();
			ClientReciever.exit();
		}catch(ClassCastException e1){
			e1.printStackTrace();
		}catch(Exception e2){
			e2.printStackTrace();
		}
		
		return result;
	}

}
