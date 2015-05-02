package server.core;

import java.util.Map;
import org.hibernate.Session;

/**
 * Интерфейс обработки запроса.
 * @author llama
 */
public interface ApiMethod {
    ApiAnswer execute(Session session, Map<String,String> params);
    
    public static class ApiAnswer {
        public final HttpCode httpCode;
        public final String body;

        public ApiAnswer(HttpCode httpCode, String answer) {
            this.httpCode = httpCode;
            this.body = answer;
        }
    }
}
