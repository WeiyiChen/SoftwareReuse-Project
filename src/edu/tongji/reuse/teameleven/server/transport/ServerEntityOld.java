package edu.tongji.reuse.teameleven.server.transport;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.tongji.reuse.teameleven.base.JsonBuilderBase;
import edu.tongji.reuse.teameleven.server.ctrl.MessageController;
import edu.tongji.reuse.teameleven.server.json.JsonAnalizerServer;
import edu.tongji.reuse.teameleven.server.json.JsonBuilderServer;

class ServerEntityOld extends Thread {
	private SocketWrapper socketController;
	private MessageController messageController;
	private List<ServerEntityOld> socketControllerList;
	private List<String> serverReceiveMsgList;
	private boolean continueToWork;

	public ServerEntityOld(SocketWrapper socketP,
						   List<ServerEntityOld> socketList) {
		this.socketController = socketP;
		this.socketControllerList = socketList;
		this.messageController = new MessageController();
		continueToWork = true;
		serverReceiveMsgList = new ArrayList<String>();
	}

	public void run() {
		BufferedReader reader = null;
		try {
			reader = socketController.getBufferedReader();
			String message = null;
			while (continueToWork) {
				message = reader.readLine();
				serverReceiveMsgList.add(message);
				saveServerMsg(serverReceiveMsgList);
				System.out.println("receive: " + message);
				if (message == null) {
					break;
				}
				String result = messageController.dealWithMessage(message);
				if (message.equals("bye")) {
					socketController.sendText("bye");
					break;
				}
				if (result.equals(JsonBuilderServer.getNeedReloginError())) {
					result = JsonBuilderServer.getReloginRequestJson();
				}
				if (result.equals(JsonBuilderServer.getMessageBusyError())) {
					continue;
				}
				System.out.println("send:    " + result);
				this.sendMessage(result);
				messageController.addSendRecord();
			}
			socketControllerList.remove(socketController);
			if (!continueToWork) {
				this.sendMessage("bye");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void quit() {
		try {
			socketController.quit();
		} catch (IOException e) {
		}
	}

	private void saveServerMsg(List<String> msg) throws IOException {
		FileWriter writer = new FileWriter("server_msg.txt");
		for (String str : msg) {
			writer.write(str);
		}
		writer.close();
	}

	private void sendMessage(String text) {
		// send message to this client or all client
		if (JsonAnalizerServer.getMessageType(text).equals(
				JsonBuilderBase.message)) {
			// send information to all user
			String group = this.messageController.getGroup();
			for (int i = 0; i < socketControllerList.size(); i++) {
				if(socketControllerList.get(i).isInGroup(group))
				socketControllerList.get(i).sendText(text);
			}
		} else {
			socketController.sendText(text);
		}
	}

	public boolean hasCurrentUser() {
		return this.messageController.hasCurrentUser();
	}

	public boolean isInGroup(String group) {
		if (group == null) {
			return false;
		}
		return group.equals(messageController.getGroup());
	}
	
	public void sendText(String text){
		this.socketController.sendText(text);
	}

}