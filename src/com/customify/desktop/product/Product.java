package com.customify.desktop.product;

import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Product {

    public Product(Socket socket) throws Exception {
        JPanel product = new ReadProductDB(socket);
        new Layout(product, "Read Business");
    }

}
