package server.api;

import java.util.Map;
import org.hibernate.Session;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.AuthSession;
import server.io.JSONHelper;
import server.logic.AuthSessionDAO;

public class AuthorizeApi implements ApiMethod {

    @Override
    public ApiAnswer execute(Session session, Map<String, String> params) {
        try {
            if (   !(  params.containsKey("mail") 
                    && params.containsKey("passwd")
                   )
                ) throw new IllegalArgumentException("Missing some input parameters!");
            AuthSession result = AuthSessionDAO.authorize(session, params.get("mail"), params.get("passwd"));
            return new ApiAnswer(HttpCode.OK, result.asJSON().toJSONString());
        } catch (IllegalArgumentException | IllegalAccessException e){
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(e));
        } 
    }
    
}
