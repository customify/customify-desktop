package com.customify.desktop.product;

import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.io.IOException;

public class Products {

    public Products() throws IOException {
        JPanel newProduct=new NewProduct();
        new Layout(newProduct);
    }


}
