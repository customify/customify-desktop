// Initialize the Desktop applications

package com.customify.desktop;


import com.customify.desktop.business.Business;
import com.customify.desktop.product.Product;
import com.customify.desktop.product.ReadProductDB;

import java.io.IOException;
import java.net.Socket;

public class Main {
    private Socket socket;
    public Main(Socket socket) throws Exception {
        this.socket=socket;
        new Product(socket);
    }
//    public static void main(String[] args) throws IOException {
////        new Sales();
//        private Socket socket;
//        new Main(this.socket);
//    }
}
