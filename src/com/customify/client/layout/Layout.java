package com.customify.client.layout;

import com.customify.client.components.Sidebar;

import javax.swing.*;
import java.awt.*;

public class Layout {
    public Layout(Container contents){
        JFrame frame = new JFrame("Layout");
        frame.setBackground(Color.white);
        JPanel sidebar = new Sidebar();

        JPanel navbar = new JPanel();
        navbar.setBackground(new Color(53,32,88));
        navbar.setBounds(300, 0, 1620, 70);

//        JPanel contents = new JPanel();
        contents.setBackground(Color.GREEN);
        contents.setBounds(300,70,1060,667);

//        contentsPanel.setLayout(null);
//        contentsPanel.setBounds(0,800,1000,600);


//        contentsPanel.setSize(new Dimension(1000,1000));
//        contentsPanel.setBackground(Color.blue);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(1920, 1000);
        frame.setVisible(true);
        frame.add(sidebar);
        frame.setResizable(false);
        frame.add(navbar);




//        contents.add(contentsPanel);

        frame.add(contents);

    }

//    public static void main(String args[]){
//        new Layout();
//    }
}
