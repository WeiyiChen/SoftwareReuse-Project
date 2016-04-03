package server.ctrl;

import java.util.Map;

import javafx.util.Pair;
import server.DAO.JsonDao;

public class ConfigController {
	private JsonDao configDao;
	private Map<String, String> confMap;
	private static final Pair<String, Integer> maxMsgsPerSecond = new Pair<String, Integer>(
			"MaxMessagesPerSecond", 5);
	private static final String maxMsgsPerLogin = "MaxMessagesPerLogin";
	private static final String saveCircle = "saveCircle";

	public ConfigController() {
		configDao = new JsonDao("config");
		confMap = configDao.read();
	}
}
