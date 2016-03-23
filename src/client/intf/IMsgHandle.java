package client.intf;

import java.util.List;
import java.util.Map;

public interface IMsgHandle {
	void sendMessage(String msg, String user);
	void sendMessage(String msg);
	
	Map<String, List<String>> getMessage();

}
