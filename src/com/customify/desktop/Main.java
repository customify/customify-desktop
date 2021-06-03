// Initialize the Desktop applications

package com.customify.desktop;
import com.customify.desktop.components.Overview;
import com.customify.desktop.components.Sidebar;
import com.customify.desktop.points.PointsServices;
import com.customify.desktop.sales.Sales;
import com.customify.desktop.business.Business;
import com.customify.desktop.product.Product;

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
        new Main("localhost");
        System.out.println("Shutting down................");
    }

    private boolean connectTOServer(String serverIp){
        int portNumber = 4000;
        try{
            Socket socket = new Socket(serverIp, portNumber);
//            while(isConnectionOn){
//
////                new Sidebar(socket);
////                new Overview().init();
//                System.out.println("Client connected to server");
//            }
//            new Sidebar(socket);
//            new Overview().init();
        }catch (Exception e){
            this.isConnectionOn = false;
            System.out.println("Failed to connect to the server at port: "+ portNumber);
            System.out.println("Exception: "+ e.toString());
        }
        return true;
    }
}