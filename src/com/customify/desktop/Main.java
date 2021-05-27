// Initialize the Desktop applications

package com.customify.desktop;

import com.customify.desktop.layout.Layout;
import com.customify.desktop.sales.Sales;
import com.customify.desktop.sales.addSales;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
    new Layout(new Sales());
    // new Layout(new addSales());
    }
}
