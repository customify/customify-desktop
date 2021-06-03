package com.customify.desktop.utils;

import com.customify.cli.utils.authorization.UserSession;
import com.customify.desktop.LoginWindow;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Socket;
import java.util.Scanner;

public class VerifyAuth {
    private Socket socket;
    public VerifyAuth()  { }
    public VerifyAuth(Socket socket) throws Exception{
        this.socket = socket;

        UserSession userSession = new UserSession();
        if(userSession.isLoggedIn())
        {
            String json = userSession.getUserJsonObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            RouteWindow route = new RouteWindow(jsonNode.get("appUser").asText(),this.socket);
        }else{
            openLogin=true;
            this.view();
        }
    }
    private boolean openLogin = false;
    public void view() throws Exception{
        LoginWindow window = new LoginWindow(getSocket());
        Scanner scan = new Scanner(System.in);
        String loginCredentials = scan.nextLine();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
