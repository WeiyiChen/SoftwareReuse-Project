package server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import base.JsonBuilderBase;
import server.ctrl.MessageController;
import server.ctrl.SocketController;
import server.json.JsonAnalizerServer;
import server.json.JsonBuilderServer;

class Server11Entity extends Thread {
	private SocketController socketController;
	private MessageController messageController;
	private List<SocketController> socketControllerList;
	private boolean continueToWork;

	public Server11Entity(SocketController socketP, List<SocketController> socketList) {
		this.socketController = socketP;
		this.socketControllerList = socketList;
		this.messageController = new MessageController();
		continueToWork = true;
	}

	public void run() {
		BufferedReader reader = null;
		try {
			reader = socketController.getBufferedReader();
			String message = null;
			while (continueToWork) {
				message = reader.readLine();
				System.out.println("receive: "+message);
				if (message == null) { break;}
				String result = messageController.dealWithMessage(message);
				if (message.equals("bye")) {
					socketController.sendText("bye");
					break;
				}
				if(result.equals(JsonBuilderServer.getNeedReloginError())){
					result = JsonBuilderServer.getReloginRequestJson();
				}
				if(result.equals(JsonBuilderServer.getMessageBusyError())){
					continue;
				}
				System.out.println("send:    "+result);
				this.sendMessage(result);
				messageController.addSendRecord();
			}
			socketControllerList.remove(socketController);
			if(!continueToWork){
				this.sendMessage("bye");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void quit(){
		messageController.quit();
		try {
			socketController.quit();
		} catch (IOException e) {
		}
	}
	
	private void sendMessage(String text) {
		// send message to this client or all client
		if (JsonAnalizerServer.getMessageType(text).equals(
				JsonBuilderBase.message)) {
			// send information to all user
			for (int i = 0; i < socketControllerList.size(); i++) {
				socketControllerList.get(i).sendText(text);
			}
		} else {
			socketController.sendText(text);
		}
	}
}