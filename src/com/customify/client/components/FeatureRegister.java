package com.customify.client.components;

import com.customify.client.layout.Layout;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//
//public class FeatureRegister {
//    public FeatureRegister(){}
//    public static JPanel init(){
//        JPanel container = new JPanel();
////        container.setBounds(300,100,1000,600);
//        container.setBackground(Color.red);
//        /*
//          @Role wrapper title
//        */
//        JLabel title = new JLabel("Register a feature");
//        title.setFont(new Font("Montserrat", Font.BOLD, 20));
//        title.setBorder(new EmptyBorder(50,0,0,0));
//        title.setForeground(new Color(53,32,88));
//        title.setBackground(Color.BLUE);
//
////        container.setBackground(Color.yellow);
//        container.add(title);
//
//
//        /*
//          @Role feature name label
//        */
////        container.setPreferredSize(new Dimension(1000,1000));
//        JLabel featureName = new JLabel("Feature Name");
//        featureName.setBounds(50,200,100,100);
//        featureName.setBackground(Color.blue);
////        title.setPreferredSize(new Dimension(1000,1000));
//
//        container.add(featureName);
//        return container;
//    }
//
//    public static void main(String args[]){
//        JPanel panel = init();
//        new Layout(panel);
//    }
//}


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FeatureRegister  {
    Container c = new Container();
    JLabel label1, label2;
    JTextField user;
    JPasswordField pass;
    JButton btn;

    public Container init(){
//        c.setTitle("Login form");
//        setSize(400, 300);
//        setLocation(100,100);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);

//        c = getContentPane();
//        c.setLayout(null);

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


//        btn.addActionListener(this);
//        se
//        tVisible(true);

        return c;
    }

//    public void actionPerformed(ActionEvent e){
//        System.out.println("Username: "+user.getText());
//        System.out.println("Password: "+pass.getPassword());
//    }

    public static void main(String args[]){
        new Layout(new FeatureRegister().init());
    }
}
