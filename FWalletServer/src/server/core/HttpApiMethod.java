package server.core;

import com.sun.net.httpserver.HttpHandler;

public interface HttpApiMethod {
    public HttpHandler getHandler();
    public String getURI();
}
