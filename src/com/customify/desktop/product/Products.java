package com.customify.desktop.product;

import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Products {

//    public Products(Socket socket) throws IOException {
//
//        JPanel newProduct=new NewProduct(socket);
//        new Layout(newProduct);
//    }

    public Products() throws IOException {
        JPanel newProduct=new NewProduct();
        new Layout(newProduct);
    }


}
