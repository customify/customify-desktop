// Initialize the Desktop applications

package com.customify.desktop;


import com.customify.desktop.business.Business;
import com.customify.desktop.sales.Sales;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        new Sales();
//        new Business();
    }
}
