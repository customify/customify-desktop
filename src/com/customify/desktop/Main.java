// Initialize the Desktop applications

package com.customify.desktop;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.sales.Sales;
import com.customify.desktop.business.Business;
import com.customify.desktop.product.Product;
import com.customify.desktop.product.ReadProductDB;
import com.customify.desktop.services.points.PointsServices;

import java.io.IOException;
import java.net.Socket;

public class Main {
    private  Socket socket;
    public Main(Socket socket) throws Exception {
        this.socket=socket;
//        new Product(socket);
        new Product(socket);

    }

    public void main(String[] args) throws  IOException {
        new Sales();
        try {
            new Main(this.socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
