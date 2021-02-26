/*
 *
 * By Verite ,  Patrick & Samuel
 * Desc: The core server loader
 *
 * */


package com.customify.server;
import com.customify.server.Db.*;
import com.customify.server.utils.*;

import java.net.ServerSocket;
import java.net.Socket;


public class Main {

    private static final int portNumber = 3000;



    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket;

        try {
            Db.init();
            serverSocket = new ServerSocket(portNumber);
            System.out.println("New server has been listening on port: " + portNumber);
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New Client is connected on the Server");
                RequestHandler con = new RequestHandler(clientSocket);
                while(true) {
                    con.init(clientSocket.getInputStream());
                }
            }
        } catch (Exception e ) {
            System.out.println("Can not listen to port: " + portNumber + ", Exception " + e);
        }
    }


}

