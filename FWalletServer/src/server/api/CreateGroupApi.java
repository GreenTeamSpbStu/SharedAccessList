package server.api;

import java.sql.Timestamp;
import java.util.Map;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Group;
import server.entity.Participant;
import server.io.JSONHelper;
import server.logic.AuthSessionDAO;
import server.logic.GroupDAO;
import server.logic.ParticipantDAO;

/**
 *
 * @author magni
 */
public class CreateGroupApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (!params.containsKey("token") || 
                    !params.containsKey("name"))
                throw new IllegalArgumentException("Missing parameter!");
            
            long userId = AuthSessionDAO.getSessionByToken(params.get("token")).getUserid();
            
            Group g = new Group()
                    .setName(params.get("name"))
                    .setOwnerId(userId)
                    .setCreationDate(new Timestamp(System.currentTimeMillis()));
                    
            if(params.containsKey("description")){
                g.setDescription(params.get("description"));
            }else{
                g.setDescription("");
            }
            
            GroupDAO.createGroup(g);
            
            Participant p = new Participant()
                    .setBalance(0)
                    .setGroupId(g.getId())
                    .setParticipantId(userId);
            ParticipantDAO.join(p);
            
            return new ApiAnswer(HttpCode.OK, "");
        }catch (NumberFormatException | IllegalAccessException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }
    }
    
}
