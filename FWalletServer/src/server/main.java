package server;

import java.io.IOException;
import javax.net.ssl.SSLContext;
import server.core.Server;
import utils.NetworkUtils;

public class main {

    private static final int threadSizePool = 4;

    public static void main(String[] args) throws IOException{
        SSLContext sslContext = null;//NetworkUtils.createSSLContext("/serverKeystore.jks", "GreenTeam2015", "GreenTeam2015");
        Server s = new Server(25565, sslContext, 4);
        s.start();
    }
}
