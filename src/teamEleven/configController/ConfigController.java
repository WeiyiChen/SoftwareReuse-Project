package teamEleven.configController;

import java.util.Map;


public class ConfigController {
	private JsonDao configDao;
	private Map<String, String> confMap;
	
	public ConfigController(){
		configDao = new JsonDao("config");
		confMap = configDao.read();
	}
	
	public ConfigController(String configFilePathName){
		configDao = new JsonDao("config");
		confMap = configDao.read();
	}
	
	public ConfigController(Map<String, String> defaultConfigMap){
		configDao = new JsonDao("config", defaultConfigMap);
		confMap = configDao.read();
	}
	
	public int getInt(String key, int defaultValue) {
		try {
			return Integer.valueOf(confMap.get(key));
		} catch (Exception e) {
		
			return defaultValue;
		}
	}
	
	public int getInt(String key){
		try {
			return Integer.valueOf(confMap.get(key));
		} catch (Exception e) {
		
			throw new RuntimeException(e);
		}
	}

	public String getString(String key, String defaultValue) {
		if(confMap.containsKey(key)){
			return confMap.get(key);
		}
		return defaultValue;
	}
	
	public String getString(String key){
		if(confMap.containsKey(key)){
			return confMap.get(key);
		}
		throw new RuntimeException("config doesn't contains " + key);
	}

	public void setString(String key, String value) {
		confMap.put(key, value);
	}

	public void setInt(String key, int value) {
		confMap.put(key, String.valueOf(value));
	}

	public void refresh() {
		this.confMap = configDao.read();
	}
	
}
