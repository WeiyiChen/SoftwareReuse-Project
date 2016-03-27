package client.intf;

import java.util.List;
import java.util.Map;

public interface IMsgHandle {
	void sendMessage(Object msg, String targetUser);
	void sendMessage(Object msg);
	
	Object getMessage();
	
}
