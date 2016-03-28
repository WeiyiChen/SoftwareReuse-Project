package client.ctl;

import java.io.IOException;

import client.intf.IAddMsgToUI;
import client.intf.IMsgHandle;
import client.intf.IMsgSender;
import client.intf.IMsgWindow;
import client.transport.ClientSocket;
import client.transport.JsonMsgSender;

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
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			result = false;
		}
		IAddMsgToUI iAddMsgToUi;
		try{
			if(msg instanceof java.lang.String){
//				iAddMsgToUi = new AddStrMsgToUI();
				iAddMsgToUi = new AddJsonMsgToUI();
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
//				iAddMsgToUi = new AddStrMsgToUI();
				iAddMsgToUi = new AddJsonMsgToUI();
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
