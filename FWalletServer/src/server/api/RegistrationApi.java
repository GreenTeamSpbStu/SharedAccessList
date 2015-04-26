package server.api;

import java.util.Map;
import org.hibernate.exception.ConstraintViolationException;
import server.core.ApiMethod;
import server.core.HttpCode;
import server.entity.User;
import server.io.JSONHelper;
import server.logic.UserDAO;

public class RegistrationApi implements ApiMethod {

    @Override
    public ApiAnswer execute(Map<String, String> params) {
        try {
            if (   !(  params.containsKey("mail") 
                    && params.containsKey("name") 
                    && params.containsKey("passwd")
                   )
                ) throw new IllegalArgumentException("Missing some input parameters!");
            User user = new User()
                    .setEmail(params.get("mail"))
                    .setName(params.get("name"))
                    .setPasswd(params.get("passwd"));
            UserDAO.register(user);
            return new ApiAnswer(HttpCode.OK, "");
        } catch (ConstraintViolationException ex){
            IllegalArgumentException e = new IllegalArgumentException("There is user with same email!"); 
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(e));
        } catch (Exception e){
            return new ApiAnswer(HttpCode.ERROR, JSONHelper.toJSON(e));
        } 
    }
    
}
