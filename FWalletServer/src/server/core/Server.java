package server.core;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import server.api.AuthorizeApi;
import server.api.CreateGroupApi;
import server.api.GetGroupApi;
import server.api.GetUserApi;
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
        server.setExecutor(Executors.newFixedThreadPool(threads));
        initialize();      
    }
    
    private void initialize(){
        addMethod(new RegistrationApi(),"/reg");
        addMethod(new AuthorizeApi(),"/auth");
        addMethod(new GetUserApi(), "/user.get");
        addMethod(new CreateGroupApi(),"/createGroup.api");
        addMethod(new GetGroupApi(), "/group.get");
    }
    
    private void addMethod(ApiMethod apiMethod, String path){
        HttpApiMehodImpl api = new HttpApiMehodImpl(
                apiMethod, 
                path);
        server.createContext(api.getURI(), api.getHandler());
    }
    
    public void start(){
        server.start();
    }
    
    public void stop(int i){
        server.stop(i);
    }
}
