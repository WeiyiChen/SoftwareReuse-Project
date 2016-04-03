/*package server.DAO;

import java.util.Map;

import org.json.JSONObject;

public class ConfigDao extends FileDAO<Map<String, String>> {

	@Override
	public boolean save(Map<String, String> map) {
		JSONObject jo = new JSONObject(map);
		checkOrCreateFile();
		return FileAccess.fileOverWrite(getPathName(), jo.toString());
	}

	@Override
	public Map<String, String> read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void checkOrCreateFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getFileName() {
		return "config.json";
	}

	@Override
	protected String getBasicString() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/