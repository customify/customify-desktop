
package com.customify.desktop;


import com.customify.desktop.business.NewBusiness;
import com.customify.desktop.customerFeedback.Businesses;
import com.customify.desktop.customerFeedback.CustomerFeedbackForm;
import com.customify.desktop.employee.UpdateEmployee;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.sales.Sales;
import com.customify.desktop.utils.VerifyAuth;

import java.awt.*;
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

            Container container = new Container();

//            new Layout(new NewBusiness(socket), "New business");
//            new Layout(new CustomerFeedbackForm(socket),"Customer feedback registration");
//            String businesses[];
//              new Sidebar(socket);
//              new Overview().init();
//            VerifyAuth auth = new VerifyAuth(socket);
            VerifyAuth auth = new VerifyAuth(socket);
//            new com.customify.desktop.Main(socket);
//            String businesses[];
//            Businesses b = new Businesses(socket);
//            b.viewAll();
//            CustomerFeedbackService c = new CustomerFeedbackService(socket);
//            System.out.println("Business: "+c.handleGetResponse());
//            System.out.println(b.viewAll().get(0));
//            System.out.println("Connected .........///");

        } catch (Exception e) {
            System.out.println("Failed to connect to the server at port: " + portNumber);
            System.out.println("Exception: " + e);
        }
        return true;
    }
}
