package client.intf;

import java.util.List;
import java.util.Map;

public interface IMsgHandle {
	void sendMessage(String msg, String targetUser);
	void sendMessage(String msg);
	
	Map<String, Object> getMessage();

}
