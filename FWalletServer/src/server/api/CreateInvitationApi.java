package server.api;

import java.util.Map;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.io.JSONHelper;
import server.logic.InvitationDAO;

public class CreateInvitationApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Session session, Map<String, String> params) {
        try {
            if (! (params.containsKey("token") && params.containsKey("groupId") && params.containsKey("recipientId")))
                return new ApiAnswer(HttpCode.OK, JSONHelper.toJSON(new IllegalArgumentException("Missing some input parameters!")));
            long groupId = Long.parseLong(params.get("groupId"));
            long recipientId = Long.parseLong(params.get("recipientId"));
            InvitationDAO.createInvitation(session, params.get("token"), groupId, recipientId);
            return new ApiAnswer(HttpCode.OK, "");
        } catch (ConstraintViolationException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(new IllegalStateException("User not found or already have invitation!")));
        } catch (IllegalAccessException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }
    }
    
}
