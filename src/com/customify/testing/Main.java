package com.customify.testing;

import com.customify.client.layout.Layout;
import com.customify.testing.businesses.NewBusiness;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JPanel body = new NewBusiness();
        new Layout(body);
    }
}
