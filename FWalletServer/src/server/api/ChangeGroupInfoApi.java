package server.api;

import java.util.Map;
import java.util.Objects;
import org.hibernate.Session;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Group;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.AuthSessionDAO;
import server.logic.GroupDAO;
import server.logic.UserDAO;

/**
 *
 * @author magni
 */
public class ChangeGroupInfoApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Session session, Map<String, String> params) {
        try {
            boolean isEmpty = true;
            
            if (!params.containsKey("token") || !params.containsKey("id"))
                throw new IllegalArgumentException("Missing parameter!");
            
            Group g = GroupDAO.getGroup(session, Long.parseLong(params.get("id")));
            
            User groupOwner = UserDAO.getUser(session, g.getOwnerId());
            User tokenHolder = AuthSessionDAO.getSessionByToken(session, params.get("token")).getUser();
            
            if(!Objects.equals(groupOwner.getId(), tokenHolder.getId()))
                throw new IllegalArgumentException("Access denied. User is not administrator of this group");
            
            if (params.containsKey("ownerId")){
                g.setOwnerId(Long.parseLong(params.get("ownerId")));
                isEmpty = false;
            }
            
            if (params.containsKey("description")){
                g.setDescription(params.get("description"));
                isEmpty = false;
            }
            
            if(params.containsKey("name")){
                g.setName(params.get("name"));
                isEmpty = false;
            }
                
            if(isEmpty)
                throw new IllegalArgumentException("No parameters specified!");
            
            GroupDAO.updateGroupInfo(session, g);
            
            return new ApiAnswer(HttpCode.OK, "");
        }catch (NumberFormatException | IllegalAccessException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }
    }
    
}
