package client.ctl;

import java.io.IOException;
import java.util.List;

import base.JsonBuilderBase;
import client.intf.IAddMsgToUI;
import client.intf.ILoginWindow;
import client.intf.IMsgHandle;
import client.intf.IMsgSender;
import client.intf.IMsgWindow;
import client.transport.ClientSocket;
import client.transport.JsonMsgSender;
import client.ui.LoginWindow;

/**
 * 
 * @author Dai
 *
 */
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
		boolean result = false;
		IMsgSender msgSender = new JsonMsgSender();
		result = msgSender.send(msg);
		if(result){
//			ClientLogger.increaseSendNum();
//			ClientRecordController crc = ClientRecordController.getInstance();
//			crc.sendNumAdd();
			ClientMonitorController.increaseSendNum();
		}	
	}
	
	
	@Override
	public void receiveAndUpdateMsg(Object msg) {
		// TODO Auto-generated method stub
		
		// relogin message is also count as a message
//		ClientLogger.increaseReceiveNum();
//		ClientRecordController crc = ClientRecordController.getInstance();
//		crc.receiveNumAdd();
		ClientMonitorController.increaseReceiveNum();
		IAddMsgToUI iAddMsgToUi;
		try{
			if(msg instanceof java.lang.String){
				
				String jsonStr = (String)msg;
				if(JsonBuilderBase.relogin.equals(JsonAnalizerClient.getMessageContent(jsonStr))){
					new WindowJumpFromMsgToLogin().jump(imw, new LoginWindow());
				}
				else{
					iAddMsgToUi = new AddJsonMsgToUI();
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

	public void handleContactsMsg(String jsonString){
		String op = JsonAnalizerClient.getString(jsonString, JsonBuilderBase.op);
		if(JsonBuilderBase.add.equals(op)){
			imw.addContact(JsonAnalizerClient.getString(jsonString, JsonBuilderBase.content));
		}
		else if(JsonBuilderBase.remove.equals(op)){
			imw.removeContact(JsonAnalizerClient.getString(jsonString, JsonBuilderBase.content));
		}
		else if(JsonBuilderBase.init.equals(op)){
			List<String> contacts = JsonAnalizerClient.getInitContacts(jsonString);
			for(String user : contacts){
				imw.addContact(user);
			}
		}
	}
	
	
}
