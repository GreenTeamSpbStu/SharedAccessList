package server.core;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import org.hibernate.Session;
import server.HibernateUtil;
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
            
            System.out.println(new Date() + "\t Hadling query: " + t.getRequestURI());

            ApiMethod.ApiAnswer answer = new ApiMethod.ApiAnswer(
                HttpCode.ERROR, 
                JSONHelper.toJSON(new InternalError("Internal server error. Please check log."))
            );
            
            try {
                answer = prepareAnswer(t);
            } catch (ExceptionInInitializerError e){
                answer = new ApiMethod.ApiAnswer(
                    HttpCode.ERROR, 
                    JSONHelper.toJSON(new InternalError("Database is unavailible!"))
                );
            } catch (Throwable e) {
                e.printStackTrace();
            }
            
            try (OutputStream out = t.getResponseBody()) {
                byte [] bytes = answer.body.getBytes("UTF-8");
                t.sendResponseHeaders(answer.httpCode.code, bytes.length);
                out.write(bytes);
            }
        }
    };
    
    private ApiMethod.ApiAnswer prepareAnswer(HttpExchange t){
        Map<String,String> query = NetworkUtils.parseURIQuery(t.getRequestURI().getQuery());
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return apiMethod.execute(session, query);
        } catch (Exception e){
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }
        
    }

}
