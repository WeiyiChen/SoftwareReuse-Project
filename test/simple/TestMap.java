package simple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by daidongyang on 5/25/16.
 */
public class TestMap {
    public static void main(String[] args){
        Map<String, String> map= new HashMap<>();
        map.put("hello", "hello");
        map.put("hello", "world");
        map.put("test", "test");
        System.out.println(map);
        String a = map.remove("hello");
        String b = map.remove("hello");
        System.out.println(a);
        System.out.println(b);
    }
}
