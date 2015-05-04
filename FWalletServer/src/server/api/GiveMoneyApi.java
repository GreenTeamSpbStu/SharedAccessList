package server.api;

import java.util.Map;
import org.hibernate.Session;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.io.JSONHelper;
import server.logic.ParticipantDAO;

public class GiveMoneyApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Session session, Map<String, String> params) {
        try {
            if (! (params.containsKey("token") && params.containsKey("groupId") && params.containsKey("recipientId") && params.containsKey("amount")))
                return new ApiAnswer(HttpCode.OK, JSONHelper.toJSON(new IllegalArgumentException("Missing some input parameters!")));
            long groupId = Long.parseLong(params.get("groupId"));
            long recipientId = Long.parseLong(params.get("recipientId"));
            float amount = Float.parseFloat(params.get("amount"));
            ParticipantDAO.giveMoney(session, params.get("token"), groupId, recipientId, amount);
            return new ApiAnswer(HttpCode.OK, "");
        } catch (Exception ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }    }
    
}
