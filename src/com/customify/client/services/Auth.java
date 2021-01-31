package com.customify.client.services;

import com.customify.shared.Keys;
import com.customify.client.Common;
import com.customify.shared.Request;
import com.customify.shared.data_format.LoginFormat;
import com.customify.shared.data_format.SignUpFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Auth {

    private Socket socket;
    private String data;

    public Auth(Socket socket){
        this.socket = socket;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;

    }

    public void login(LoginFormat format) throws IOException {
        Request request = new Request(Keys.LOGIN,format);
        Common common = new Common(request,this.socket);
    }
    public void signUp(SignUpFormat format) throws IOException {
        String key ="USER_SIGNUP";
        Request request = new Request(Keys.REGISTER,format);
        Common common = new Common(request,this.socket);

    }

}
