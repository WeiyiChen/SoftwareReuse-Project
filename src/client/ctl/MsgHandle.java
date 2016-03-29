package client.ctl;

import java.io.IOException;

import client.intf.IAddMsgToUI;
import client.intf.ILoginWindow;
import client.intf.IMsgHandle;
import client.intf.IMsgSender;
import client.intf.IMsgWindow;
import client.transport.ClientSocket;
import client.transport.JsonMsgSender;
import client.ui.LoginWindow;
import client.util.ClientLogger;

public class MsgHandle implements IMsgHandle{
	private IMsgWindow imw;
	
	public MsgHandle(){}
	public MsgHandle(IMsgWindow _imw){ imw = _imw;}
	
	@Override
	public void sendMessage(Object msg, String targetUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendMessage(Object msg) {
		// TODO Auto-generated method stub
		boolean result;
		IMsgSender msgSender = new JsonMsgSender();
		try {
			result = msgSender.send(ClientSocket.getSocket(), msg);
			ClientLogger.increaseSendNum();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			ClientLogger.setIsConnect(false);
			e1.printStackTrace();
			result = false;
		}
//		IAddMsgToUI iAddMsgToUi;
//		try{
//			if(msg instanceof java.lang.String){
////				iAddMsgToUi = new AddStrMsgToUI();
//				iAddMsgToUi = new AddJsonMsgToUI();
//				iAddMsgToUi.addMsg(imw, msg);
//			}
//			else{
//				iAddMsgToUi = new AddStrMsgToUI();
//				iAddMsgToUi.addMsg(imw, msg.toString());
//			}
//		}catch(ClassCastException e){
//			e.printStackTrace();
//		}
		
	}
	
	
	@Override
	public void receiveAndUpdateMsg(Object msg) {
		// TODO Auto-generated method stub
		
		// relogin message is also count as a message
		ClientLogger.increaseReceiveNum();
		IAddMsgToUI iAddMsgToUi;
		try{
			if(msg instanceof java.lang.String){
//				iAddMsgToUi = new AddStrMsgToUI();
				iAddMsgToUi = new AddJsonMsgToUI();
				String jsonStr = (String)msg;
				if(jsonStr.equals(JsonBuilderClient.getReloginRequestJson())){
					imw.closeMsgWindow();
					ILoginWindow ilw = new LoginWindow();
					ilw.setTip("redo login!");
					ilw.showLoginWindow();
					ClientLogger.setIsLogin(false);
				}
				else{
					iAddMsgToUi.addMsg(imw, msg);
				}
				
			}
			else{
				iAddMsgToUi = new AddStrMsgToUI();
				iAddMsgToUi.addMsg(imw, msg.toString());
			}
		}catch(ClassCastException e){
			e.printStackTrace();
		}
	}
	
	
}
