package com.customify.desktop.business;

import com.customify.desktop.layout.Layout;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;

public class Business {

    public Business(Socket socket) throws IOException, ClassNotFoundException {
        JPanel business = new ReadBusiness(socket);
        new Layout(business, "Read Business");
    }
}
