package com.customify.desktop;


import com.customify.desktop.business.Business;
import com.customify.desktop.business.NewBusiness;
import com.customify.desktop.customerFeedback.CustomerFeedbackForm;
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
            // 1. view all businesses

             new Business(socket);

            // 2. Create a new business

//             new Layout(new NewBusiness(socket), "Create a new business ");

            // feedbacks

            // 1. View all feedbacks
//            new Layout(new CustomerFeedbackForm(socket),"Customer feedback registration");

        } catch (Exception e) {
            System.out.println("Failed to connect to the server at port: " + portNumber);
            System.out.println("Exception: " + e);
        }
        return true;
    }
}
