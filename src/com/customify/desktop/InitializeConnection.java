package com.customify.desktop;


import com.customify.desktop.business.ReadBusiness;
import com.customify.desktop.customerFeedback.ReadFeedbacks;
import com.customify.desktop.layout.Layout;

import java.net.Socket;

public class InitializeConnection {

    public static void main(String[] args) {
        new InitializeConnection("localhost");
    }

    public InitializeConnection(String serverIP) {
        if (!connectTOServer(serverIP)) {
            System.out.println("Failed to connect to the server to: " + serverIP);
        }
    }

    private boolean connectTOServer(String serverIp) {
        int portNumber = 3000;
        try {
            Socket socket = new Socket(serverIp, portNumber);

//            new Layout(new NewBusiness(socket), "New business");
//            new Layout(new CustomerFeedbackForm(socket),"Customer feedback registration");

//            new Layout(new ReadFeedbacks(socket),"Fetch feedbacks");
            new Layout(new ReadBusiness(socket),"jjjj");
        } catch (Exception e) {
            System.out.println("Failed to connect to the server at port: " + portNumber);
            System.out.println("Exception: " + e);
        }
        return true;
    }
}
