// Initialize the Desktop applications

package com.customify.desktop;


import com.customify.desktop.business.Business;
import com.customify.desktop.sales.Sales;

import java.io.IOException;
import java.net.Socket;

public class Main {
//    private Socket socket;
//    public Main(Socket socket) throws IOException, ClassNotFoundException {
//        this.socket=socket;
//        new Business(socket);
//    }
    public static void main(String[] args) throws IOException {
        new Sales();
//        new Business();
    }
}
