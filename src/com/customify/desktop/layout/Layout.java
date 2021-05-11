package com.customify.desktop.layout;

import com.customify.desktop.components.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Layout {
    public Layout(Container body) throws IOException {
        JFrame frame = new JFrame("Layout");
        frame.setBackground(Color.white);
        JPanel sidebar = new Sidebar();
        JPanel navbar = new JPanel();
        navbar.setBackground(new Color(53,32,88));
        navbar.setBounds(300, 0, 1620, 70);
        body.setBackground(Color.GREEN);
        body.setBounds(300,70,1060,667);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1920, 900);
        frame.setVisible(true);
        frame.add(body);
        frame.add(sidebar);
        frame.setResizable(true);
        frame.add(navbar);
    }

}
