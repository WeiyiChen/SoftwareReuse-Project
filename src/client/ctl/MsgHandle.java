package client.ctl;

import client.intf.IAddMsgToUI;
import client.intf.IMsgHandle;
import client.intf.IMsgWindow;

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
		IAddMsgToUI iAddMsgToUi;
		try{
			if(msg instanceof java.lang.String){
				iAddMsgToUi = new AddStrMsgToUI();
				iAddMsgToUi.addMsg(imw, msg);
			}
			else{
				iAddMsgToUi = new AddStrMsgToUI();
				iAddMsgToUi.addMsg(imw, msg.toString());
			}
		}catch(ClassCastException e){
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void receiveAndUpdateMsg(Object msg) {
		// TODO Auto-generated method stub
		IAddMsgToUI iAddMsgToUi;
		try{
			if(msg instanceof java.lang.String){
				iAddMsgToUi = new AddStrMsgToUI();
				iAddMsgToUi.addMsg(imw, msg);
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
