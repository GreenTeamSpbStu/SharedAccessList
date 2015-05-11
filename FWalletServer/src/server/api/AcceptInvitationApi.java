package server.api;

import java.util.Map;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.io.JSONHelper;
import server.logic.InvitationDAO;

public class AcceptInvitationApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (!(params.containsKey("token") && params.containsKey("id"))) throw new IllegalArgumentException("Missing some input parameters!");
            long id = Long.parseLong(params.get("id"));
            InvitationDAO.acceptInvitation(params.get("token"), id, params.containsKey("reject"));
            return new ApiAnswer(HttpCode.OK, "");
        } catch (IllegalAccessException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }  
    }
    
}
