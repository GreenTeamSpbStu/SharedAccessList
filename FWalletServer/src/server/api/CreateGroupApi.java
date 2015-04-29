package server.api;

import java.sql.Timestamp;
import java.util.Map;
import org.json.simple.JSONObject;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Group;
import server.io.JSONHelper;
import server.logic.GroupDAO;

/**
 *
 * @author magni
 */
public class CreateGroupApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (!params.containsKey("token") || 
                    !params.containsKey("name") || 
                    !params.containsKey("ownerId")) 
                throw new IllegalArgumentException("Missing parameter!");
            
            Group g = new Group()
                    .setName(params.get("name"))
                    .setDescription(params.get("description"))
                    .setOwnerId(Long.parseLong(params.get("ownerId")))
                    .setCreationDate(new Timestamp(System.currentTimeMillis()));
                    
            GroupDAO.createGroup(g);
            
            return new ApiAnswer(HttpCode.OK, "");
        }catch (NumberFormatException ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }catch (Exception ex) {
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(ex));
        }
    }
    
}
