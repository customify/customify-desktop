package com.customify.testing;

import com.customify.client.layout.Layout;
import com.customify.testing.businesses.NewBusiness;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JPanel body = new NewBusiness();
        new Layout(body);
    }
}
