
package server.io;

import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONHelper {
    public static JSONArray toJSON(List<? extends JSONAble> source){
        JSONArray result = new JSONArray();
        for (JSONAble element : source) {
            result.add(element.asJSON());
        }
        return result;
    }
    
    public static String toJSON(Throwable e){
        JSONObject json = new JSONObject();
        json.put("exception", e.getClass().getSimpleName());
        json.put("message", e.getMessage());
        return json.toJSONString();
    }
    
}
