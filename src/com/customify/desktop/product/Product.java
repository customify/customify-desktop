package com.customify.desktop.product;

import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Product {

    public Product(Socket socket) throws Exception {
        //Testing Print
        System.out.println("Entered Product Method");

        JPanel product = new ReadProductDBSearch(socket);

        //Testing Print
        System.out.println("Ended to call ReadProductDB");

        new Layout(product, "Read Business");


    }

}
