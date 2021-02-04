package com.customify.server.routes;

import java.io.IOException;
import java.net.Socket;
import com.customify.shared.Keys;

public class HandleRoutes {
    private final Keys key;
    Socket socket;
    AuthRoute authRoute;
    BussinessRoute bussinessRoute;

    public HandleRoutes(Keys key, Socket socket) throws IOException {
        this.socket = socket;
        this.authRoute = new AuthRoute(socket);
        this.key = key;
        this.switchRoutes();
    }

    public void switchRoutes() throws IOException {
        switch (this.key) {
            case LOGIN:
                authRoute.loginRoute();
                break;
            case GET_BUSS:
                bussinessRoute.getAllRoute();
                break;
            case GET_BUSS_BYID:
                bussinessRoute.getByIdroute();
                break;
            case REMOVE_BUSS:
                bussinessRoute.deleteBusinessroute();
                break;

            default:
                System.out.println("Invalid key");
        }
        authRoute.loginError();
    }
}
