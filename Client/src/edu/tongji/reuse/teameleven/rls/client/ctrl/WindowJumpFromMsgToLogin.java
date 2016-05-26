package edu.tongji.reuse.teameleven.rls.client.ctrl;

import edu.tongji.reuse.teameleven.rls.client.intf.IClientWindow;
import edu.tongji.reuse.teameleven.rls.client.intf.ILoginWindow;
import edu.tongji.reuse.teameleven.rls.client.intf.IMsgWindow;
import edu.tongji.reuse.teameleven.rls.client.intf.IWindowJump;
import edu.tongji.reuse.teameleven.rls.client.transport.ClientReciever;

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
