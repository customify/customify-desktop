package com.customify.desktop;


import com.customify.desktop.business.NewBusiness;
import com.customify.desktop.customerFeedback.Businesses;
import com.customify.desktop.customerFeedback.CustomerFeedbackForm;
import com.customify.desktop.employee.CreateEmp;
import com.customify.desktop.employee.UpdateEmployee;
import com.customify.desktop.layout.Layout;

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
            container.add(new CreateEmp(socket).init());

            new Layout(container,"Create Employee");

            /*Container container = new Container();
            container.add(new UpdateEmployee(socket).init());

            new Layout(container,"Update Employee");*/

//            new Layout(new NewBusiness(socket), "New business");
//            new Layout(new CustomerFeedbackForm(socket),"Customer feedback registration");

        } catch (Exception e) {
            System.out.println("Failed to connect to the server at port: " + portNumber);
            System.out.println("Exception: " + e);
        }
        return true;
    }
}
