package com.customify.ui.layout;

import com.customify.ui.components.Sidebar;

import javax.swing.*;
import java.awt.*;

public class Layout {
    public Layout(){
        JFrame frame = new JFrame("Layout");

        JPanel sidebar = new Sidebar();

        JPanel navbar = new JPanel();
        navbar.setBackground(new Color(53,32,88));
        navbar.setBounds(300, 0, 1620, 70);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1920, 1000);
        frame.setVisible(true);
        frame.add(sidebar);
        frame.setResizable(false);
        frame.add(navbar);
    }
}
