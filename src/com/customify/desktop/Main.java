package com.customify.desktop;


import com.customify.desktop.pages.LoginWindow;
import com.customify.desktop.pages.customer.CreateWindow;
import com.customify.desktop.utils.VerifyAuth;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
        new Main("localhost");
        System.out.println("Shutting down................");
    }

    private boolean connectTOServer(String serverIp){
        int portNumber = 5000;
        try{
            Socket socket = new Socket(serverIp, portNumber);
            while(isConnectionOn){
//                VerifyAuth auth = new VerifyAuth(socket);
                CreateWindow customer = new CreateWindow(socket);
                Scanner scan = new Scanner(System.in);
                String name = scan.nextLine();
            }
        }catch (Exception e){
            this.isConnectionOn = false;
            System.out.println("Failed to connect to the server at port: "+ portNumber);
            System.out.println("Exception: "+ e.toString());
        }
        return true;
    }
}