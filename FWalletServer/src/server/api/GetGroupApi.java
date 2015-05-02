package server.api;

import java.util.Map;
import org.hibernate.Session;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Group;
import server.io.JSONHelper;
import server.logic.GroupDAO;

public class GetGroupApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Session session, Map<String, String> params) {
        try {
            if (!params.containsKey("id")) throw new IllegalArgumentException("Missing some input parameters!");
            long id = Long.parseLong(params.get("id"));
            Group result = GroupDAO.getGroup(session, id);
            return new ApiAnswer(HttpCode.OK, result.asJSON().toJSONString());
        } catch (IllegalArgumentException | IllegalAccessException e){
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(e));
        }   
    }
    
}
