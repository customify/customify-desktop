package com.customify.desktop.products;

import com.customify.desktop.business.ReadBusiness;
import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Products {

    public Products() throws IOException {
        JPanel newProduct=new NewProduct();
        new Layout(newProduct);
    }


}
