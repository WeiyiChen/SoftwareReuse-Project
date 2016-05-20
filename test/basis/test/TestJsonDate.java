package basis.test;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daidongyang on 5/21/16.
 */
public class TestJsonDate{

    public static void main(String[] args) throws JSONException{
        TestJsonDate testJsonDate = new TestJsonDate();
//        testJsonDate.testJsonStringer();
        testJsonDate.testJsonObject();
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
        Date d3 = (Date)obj.get("date");
        System.out.println(formatter.format(d3));
    }

    public void testJsonStringer() throws JSONException{
        Date d = new Date();
        String jsonString = new JSONStringer().object().key("date").value(d).endObject().toString();
        System.out.println(jsonString);
    }
}
