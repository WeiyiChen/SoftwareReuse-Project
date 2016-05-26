package simple;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by daidongyang on 5/21/16.
 */
public class TestJson {

    public static void main(String[] args) throws JSONException{
        TestJson testJson = new TestJson();
//        testJsonDate.testJsonStringer();
//        testJson.testJsonObject();
        testJson.testJsonStrArray();
    }

    public void testJsonStrArray() throws JSONException{
        List<String> strArray = new ArrayList<String>();
        strArray.add("hello");
        strArray.add("world");
        JSONArray jsonArray = new JSONArray(strArray);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("strArray", jsonArray);
        String jsonString = jsonObject.toString();
        JSONObject jsonObject1 = new JSONObject(jsonString);
        System.out.println(jsonString);
        JSONArray jsonArray1 = jsonObject1.getJSONArray("strArray");
        System.out.println(jsonArray1);
        for(int i = 0; i < jsonArray1.length(); i++){
            System.out.println(jsonArray1.getString(i));
        }
//        String[] strings = (String[])jsonObject1.get("strArray");
//        System.out.println(strings);
//        System.out.println(strArray);
    }

    public void testJsonObject() throws JSONException{
        Date d = new Date();
        JSONObject obj = new JSONObject();
        obj.put("name", "name");
        obj.put("date", d);
        System.out.println(obj);
        Date d2 = (Date)obj.get("date");
        SimpleDateFormat formatter = new SimpleDateFormat("K:mm a, yyyy-MM-dd");
        System.out.println(formatter.format(d2));
        JSONObject obj2 = new JSONObject(obj.toString());

    }

    public void testJsonStringer() throws JSONException{
        Date d = new Date();
        String jsonString = new JSONStringer().object().key("date").value(d).endObject().toString();
        System.out.println(jsonString);
    }
}
