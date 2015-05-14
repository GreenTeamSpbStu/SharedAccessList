package server.api;

import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.Group;
import server.entity.Participant;
import server.io.JSONHelper;
import server.logic.GroupDAO;
import server.logic.ParticipantDAO;

public class GetGroupApi implements ApiMethod{

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        JSONObject result = new JSONObject();
        
        try {
            if (!params.containsKey("groupId")){
                throw new IllegalArgumentException("Missing input parameters!");
            }else{
                Group g = GroupDAO.getGroup(Long.parseLong(params.get("groupId")));
                result.put("group", g.asJSON());
            }
            
            if(params.containsKey("participants")){
                if (!params.containsKey("token")){
                    throw new IllegalArgumentException("Missing token parameter!");
                }else{
                    List<Participant> p = ParticipantDAO.getAll(Long.parseLong(params.get("groupId")));
                    result.put("participants", JSONHelper.toJSON(p));
                }
            }            
            
            return new ApiAnswer(HttpCode.OK, result.toJSONString());
        } catch (IllegalArgumentException | IllegalAccessException e){
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(e));
        }   
    }
    
}
