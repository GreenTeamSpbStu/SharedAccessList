package server.api;

import java.util.Map;
import java.util.Objects;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Group;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.AuthSessionDAO;
import server.logic.GroupDAO;
import server.logic.ParticipantDAO;
import server.logic.UserDAO;

/**
 *
 * @author magni
 */
public class KickUserFromGroupApi implements ApiMethod {

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (!params.containsKey("token")
                    || !params.containsKey("groupId")
                    || !params.containsKey("userId")) {
                throw new IllegalArgumentException("Missing parameter!");
            }

            Group g = GroupDAO.getGroup(Long.parseLong(params.get("groupId")));

            User groupOwner = UserDAO.getUser(g.getOwnerId());
            User tokenHolder = AuthSessionDAO.getSessionByToken(params.get("token")).getUser();

            if (!Objects.equals(groupOwner.getId(), tokenHolder.getId())) {
                throw new IllegalArgumentException("Access denied. You are not administrator of " + g.getName());
            }
            
            if (Objects.equals(groupOwner.getId(), Long.parseLong(params.get("userId")))) {
                throw new IllegalArgumentException("Seriously? You cant kick yourself");
            }
            
            ParticipantDAO.leave(Long.parseLong(params.get("groupId")), Long.parseLong(params.get("userId")));
            
            // cash recalculation here ///////////////////////////

            return new ApiAnswer(HttpCode.OK, "");
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }
    }

}
