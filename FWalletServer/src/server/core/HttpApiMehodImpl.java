package server.core;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import server.io.JSONHelper;
import utils.NetworkUtils;

public class HttpApiMehodImpl implements HttpApiMethod {

    final String URI;
    final ApiMethod apiMethod;
    
    public HttpApiMehodImpl(ApiMethod apiMethod, String URI) {
        this.URI = URI;
        this.apiMethod = apiMethod;
    }
    
    @Override
    public HttpHandler getHandler() {
        return handler;
    }

    @Override
    public String getURI() {
        return URI;
    }
    
    private final HttpHandler handler = new HttpHandler() {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
            ApiMethod.ApiAnswer answer;
                    try {
                        answer = apiMethod.execute(query);
                    } catch (Exception e) {
                        e.printStackTrace();
                        answer = new ApiMethod.ApiAnswer(
                                HttpCode.ERROR, 
                                JSONHelper.toJSON(new InternalError("Internal server error. Please check log."))
                        );
                    }
            
            try (OutputStream out = t.getResponseBody()) {
                byte [] bytes = answer.body.getBytes("UTF-8");
                t.sendResponseHeaders(answer.httpCode.code, bytes.length);
                out.write(bytes);
            }
        }
    };
    
}
