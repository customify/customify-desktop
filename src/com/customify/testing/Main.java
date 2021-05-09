package com.customify.testing;
/*
  @TODO fix the layout tests
*/

import com.customify.client.layout.Layout;
import com.customify.testing.businesses.NewBusiness;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JPanel container = new NewBusiness();
        new Layout(container);
    }
}
