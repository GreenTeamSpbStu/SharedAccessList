package server.api;

import java.util.Map;
import org.json.simple.JSONObject;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.io.JSONHelper;
import server.logic.UserDAO;


public class GetUserApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (!params.containsKey("token")) throw new IllegalArgumentException("Missing token parameter!");
            JSONObject result = new JSONObject();
            if (params.containsKey("profile")) {
                result.put("profile", UserDAO.getUserByToken(params.get("token")).asJSON());
            }
            return new ApiAnswer(HttpCode.OK, result.toJSONString());
        } catch (Exception ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }
    }
    
}
