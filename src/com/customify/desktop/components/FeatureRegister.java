package com.customify.desktop.components;

import com.customify.desktop.enums.UserRoles;
import com.customify.desktop.layout.Layout;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FeatureRegister  {
    Container c = new Container();
    JLabel label1, label2;
    JTextField user;
    JPasswordField pass;
    JButton btn;
    public Layout layout;

    public FeatureRegister() throws IOException {
//        this.init();
    }
    public void init() throws IOException {
        JLabel title = new JLabel("Register a feature");
        title.setFont(new Font("Montserrat", Font.BOLD, 20));
        title.setBounds(400,20,300,100);
        title.setBorder(new EmptyBorder(50,0,0,0));
        title.setForeground(new Color(53,32,88));
        c.add(title);

        label1 = new JLabel("Feature Name");
        label1.setFont(new Font("Montserrat", Font.BOLD, 17));

        label2 = new JLabel("Description");
        label2.setFont(new Font("Montserrat", Font.BOLD, 17));

        label1.setBounds(200,200,200,20);
        label2.setBounds(200,250,200,20);

        c.add(label1);
        c.add(label2);

        user  = new JTextField();
        user.setBounds(400,200,400,40);
        c.add(user);

        pass = new JPasswordField();
        pass.setBounds(400,250,400,60);
        c.add(pass);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(400,400,180,40);
        c.add(cancel);

        btn = new JButton("Create");
        btn.setBounds(620,400,180,40);
        btn.setBackground(new Color(53,32,88));
        btn.setForeground(Color.white);
        c.add(btn);
        c.setBackground(Color.red);
//        return c;

        layout = new Layout(c, "Feature register");



    }

//    public static void main(String args[]) throws IOException {
//        new FeatureRegister().init();
//    }
}
