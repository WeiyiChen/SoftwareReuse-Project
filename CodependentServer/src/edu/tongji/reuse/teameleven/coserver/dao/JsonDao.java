package edu.tongji.reuse.teameleven.coserver.dao;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class JsonDao extends FileDao<Map<String, String>>{

    protected String fileName = "";

    private Map<String, String> defaultContentMap;


    public JsonDao(String fileName){
        super();
        this.fileName = fileName.endsWith(".json")?fileName:fileName+".json";
        this.defaultContentMap = new HashMap<String, String>();
        checkOrCreateFile();
    }

    public JsonDao(String fileName, Map<String, String> defaultContentMap){
        super();
        this.fileName = fileName.endsWith(".json")?fileName:fileName+".json";
        this.defaultContentMap = new HashMap<String, String>(defaultContentMap);
        checkOrCreateFile();
    }

    @Override
    public boolean save(Map<String, String> userMap) {
        JSONObject jsonObj = new JSONObject(userMap);
        checkOrCreateFile();
        return FileAccess.fileOverWrite(getPathName(), jsonObj.toString());
    }

    @Override
    public Map<String, String> read() {
//		return null;
        checkOrCreateFile();
        String jsonString = FileAccess.readFile(getPathName());
        // todo comment below system.out
        System.out.println(getPathName());
        Map<String, String> userMap = new HashMap<String, String>();
        if(jsonString.equals("")){
            return userMap;
        }
        try{
            JSONObject jsonObj = new JSONObject(jsonString);
            @SuppressWarnings("unchecked")
            Iterator<String> it = jsonObj.keys();
            while(it.hasNext()){
                String key = it.next();
                userMap.put(key, jsonObj.getString(key));
            }
            return userMap;
        }catch(JSONException e){
            e.printStackTrace();
        }
        return userMap;
    }

    @Override
    protected void checkOrCreateFile() {
        super.checkOrMk();
    }

    @Override
    protected String getFileName() {

        return fileName;
    }

    @Override
    protected String getBasicString() {
//		return null;
        JSONObject jo = new JSONObject(defaultContentMap);
        return jo.toString();
    }

}

