package server.core;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import server.api.AuthorizeApi;
import server.api.RegistrationApi;

public class Server {
    
    private final HttpServer server;

    public Server(int port, SSLContext sslContext, int threads) throws IOException {
        if (sslContext!=null) {
            HttpsServer serverInit = HttpsServer.create(new InetSocketAddress(port), 0);
            serverInit.setHttpsConfigurator(new HttpsConfigurator(sslContext));
            server = serverInit;
        } else {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        }
        server.setExecutor(Executors.newFixedThreadPool(threads)); // creates a default executor
        initialize();      
    }
    
    private void initialize(){
        HttpApiMehodImpl registrationApi = new HttpApiMehodImpl(
                new RegistrationApi(), 
                "/reg.api");
        addMethod(registrationApi);
        
        HttpApiMehodImpl authorizationApi = new HttpApiMehodImpl(
                new AuthorizeApi(), 
                "/auth.api");
        addMethod(authorizationApi);
        
        
    }
    
    private void addMethod(HttpApiMethod method){
        server.createContext(method.getURI(), method.getHandler());
    }
    
    public void start(){
        server.start();
    }
    
    public void stop(int i){
        server.stop(i);
    }
}
