package edu.tongji.reuse.teameleven.client.ctrl;

import edu.tongji.reuse.teameleven.client.intf.IMsgHandle;
import edu.tongji.reuse.teameleven.client.intf.IMsgWindow;
import edu.tongji.reuse.teameleven.client.transport.StrMsgSender;
import edu.tongji.reuse.teameleven.codependent.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.client.intf.IAddMsgToUI;
import edu.tongji.reuse.teameleven.client.intf.IMsgSender;
import edu.tongji.reuse.teameleven.client.transport.StrLoginMsgSender;
import edu.tongji.reuse.teameleven.client.ui.LoginWindow;

import java.util.List;

/**
 * @author Dai
 */
public class MsgHandle implements IMsgHandle {
    private IMsgWindow imw;

    public MsgHandle() {
    }

    public MsgHandle(IMsgWindow _imw) {
        imw = _imw;
    }

    @Override
    public void sendMessage(Object msg, String targetUser) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sendMessage(Object msg) {

        boolean result = false;
        IMsgSender msgSender = new StrMsgSender();
        result = msgSender.send(msg);
        if (result) {
//			ClientLogger.increaseSendNum();
//			ClientRecordController crc = ClientRecordController.getInstance();
//			crc.sendNumAdd();
            ClientMonitorController.increaseSendNum();
        }
    }


    @Override
    public void receiveAndUpdateMsg(Object msg) {


        // relogin message is also count as a message
//		ClientLogger.increaseReceiveNum();
//		ClientRecordController crc = ClientRecordController.getInstance();
//		crc.receiveNumAdd();
        ClientMonitorController.increaseReceiveNum();
        IAddMsgToUI iAddMsgToUi;
        try {
            if(msg == null){
                // todo close the socket
                return;
            }
            if (msg instanceof String) {

                String jsonStr = (String) msg;
                // todo delete System.out code
                System.out.println("Receive : " + jsonStr);

                if (jsonStr.equals(JsonBuilderBase.getReloginRequestJson())) {
                    // todo when the message socket is closed, jump automatically
                    new WindowJumpFromMsgToLogin().jump(imw, new LoginWindow());
                } else if (JsonBuilderBase.contacts.equals(JsonAnalizerClient.getString(jsonStr, JsonBuilderBase.type))) {
                    handleContactsMsg(jsonStr);
                } else {
                    iAddMsgToUi = new AddJsonMsgToUI();
                    iAddMsgToUi.addMsg(imw, msg);
                }
            } else {
                iAddMsgToUi = new AddStrMsgToUI();
                iAddMsgToUi.addMsg(imw, msg.toString());
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public void handleContactsMsg(String jsonString) {
        String op = JsonAnalizerClient.getString(jsonString, JsonBuilderBase.op);
        if (JsonBuilderBase.add.equals(op)) {
            imw.addContact(JsonAnalizerClient.getString(jsonString, JsonBuilderBase.content));
        } else if (JsonBuilderBase.remove.equals(op)) {
            imw.removeContact(JsonAnalizerClient.getString(jsonString, JsonBuilderBase.content));
        } else if (JsonBuilderBase.init.equals(op)) {
            List<String> contacts = JsonAnalizerClient.getInitContacts(jsonString);
            System.out.println(contacts);
            for (String user : contacts) {
                imw.addContact(user);
            }
        }
    }




}
