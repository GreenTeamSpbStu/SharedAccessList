package server.api;

import java.util.Map;
import org.json.simple.JSONObject;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.InvitationDAO;
import server.logic.UserDAO;


public class GetUserApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (!params.containsKey("token")) throw new IllegalArgumentException("Missing token parameter!");
            JSONObject result = new JSONObject();
            User user = UserDAO.getUserByToken(params.get("token"));
            if (params.containsKey("profile")) {
                result.put("profile", user.asJSON());
            }
            if (params.containsKey("invitations")) {
                result.put("invitaitons", JSONHelper.toJSON(InvitationDAO.getInvitations(user.getId())));
            }
            return new ApiAnswer(HttpCode.OK, result.toJSONString());
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }
    }
    
}
