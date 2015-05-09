package server.api;

import java.util.Map;
import org.hibernate.Session;
import org.json.simple.JSONObject;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.AuthSession;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.AuthSessionDAO;
import server.logic.UserDAO;


public class GetUserApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Session session, Map<String, String> params) {
        try {
            JSONObject result = new JSONObject();
            if (params.containsKey("id")) {
                long id = Long.parseLong(params.get("id"));
                result.put("profile", UserDAO.getUser(session, id).asJSON());
                return new ApiAnswer(HttpCode.OK, result.toJSONString());
            }
            if (!params.containsKey("token")) throw new IllegalArgumentException("Missing token parameter!");
            AuthSession auth = AuthSessionDAO.getSessionByToken(session, params.get("token"));
            User user = auth.getUser();
            if (params.containsKey("profile")) {
                result.put("profile", user.asJSON());
            }
            if (params.containsKey("invitations")) {
                result.put("invitaitons", JSONHelper.toJSON(user.getInvitations()));
            }
            if (params.containsKey("groups")) {
               result.put("groups", JSONHelper.toJSON(user.getGroups()));
            }
            return new ApiAnswer(HttpCode.OK, result.toJSONString());
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }
    }
    
}
