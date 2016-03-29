package server.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import base.JsonBuilderBase;
import server.ctrl.MessageController;
import server.ctrl.RecordController;
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
		PrintWriter writer = null;
		try {
			reader = socketController.getBufferedReader();

			String message = null;
			while (continueToWork) {
				message = reader.readLine();
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