package com.customify.client;

import com.customify.client.views.Home;

import java.io.*;
import java.net.*;

public class Main {
    private OutputStream output = null;
    private ObjectOutputStream objectOutput = null;
    private boolean isConnectionOn = true;

    public Main(String serverIP){
        if(!connectTOServer(serverIP)){
            System.out.println("Failed to connect to the server to: "+serverIP);
        }
    }
    public static void main(String[] args){
        //System.out.println("Customify  is booting..........");
        new Main("localhost");
        System.out.println("Shutting down................");
    }
    private boolean connectTOServer(String serverIp){
        int portNumber = 3000;
        try{
            Socket socket = new Socket(serverIp, portNumber);
         //   System.out.println("Connected to the server "+ socket.getInetAddress() + " on port "+ socket.getPort());
         //   System.out.println("from local Address: "+ socket.getLocalAddress()+" and port "+ socket.getLocalPort());

            while(isConnectionOn){
                new Home(socket).view();
//                Login log = new Login(socket);
//                log.view();
            }
        }catch (Exception e){
            this.isConnectionOn = false;
            System.out.println("Failed to connect to the server at port: "+ portNumber);
            System.out.println("Exception: "+ e.toString());
        }
        return true;
    }
}