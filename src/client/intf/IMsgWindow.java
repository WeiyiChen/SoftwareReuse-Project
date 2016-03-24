package client.intf;

import java.util.List;
import java.util.Map;

public interface IMsgWindow {
	void sendMsg();
	void receiveMsg(Map<String, Object> msgs);
}
