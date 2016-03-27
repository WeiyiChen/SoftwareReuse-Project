package server.DAO;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.parser.ParseException;
import org.json.parser.JSONParser;

public class UserDAO {
	public  static JSONArray jsonDatabase = new JSONArray();
	
	public String getPassword(String user) {
		List<JSONObject> jsonObject = new ArrayList<JSONObject>();
		
	}
	
	public void addUser(String user, String password) {
		
	}
	
	public void creatJsonDatabase(){
		JSONParser parser = new JSONParser();
		try{
			FileInputStream jsonFile = new FileInputStream("/Users/chenweiyi/Desktop/jsonDatabase");
			StringBuffer txtContent = new StringBuffer();
			String line;
			while((line = jsonFile.read()) != -1){
				txtContent.append(line);
			}
			jsonFile.close();
			Object obj = parser.parse(line.toString());
			jsonDatabase = (JSONArray)obj;
		}catch(ParseException pe){
			
	         System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
	    }catch (FileNotFoundException e)
		{
	    	e.printStackTrace();
	    }catch (IOException e)
	    {
	    	e.printStackTrace();
	   	}
		
	}
	
}
