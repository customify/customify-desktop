package com.customify.desktop.product;

import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.io.IOException;

public class Product {

    public Product() throws IOException {
        JPanel product = new ReadProduct();
        new Layout(product, "Read Business");
    }

}
