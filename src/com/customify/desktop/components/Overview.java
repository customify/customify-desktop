package com.customify.desktop.components;

import com.customify.desktop.enums.UserRoles;
import com.customify.desktop.layout.Layout;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;


public class Overview extends  JFrame{
    Container c = new Container();
    JLabel label1, label2;
    JTextField user;
    JPasswordField pass;
    JButton btn;
    JFrame frame = new JFrame();
    Layout layout;
    public  Socket socket;

    public Overview(Socket socket){
        this.socket  = socket;
    }

    public Overview() {

    }

    public void init(Socket socket) throws IOException {
        JLabel title = new JLabel("Welcome to Customify ");
        title.setFont(new Font("Montserrat", Font.BOLD, 20));
        title.setBounds(400,20,300,100);
        title.setBorder(new EmptyBorder(50,0,0,0));
        title.setForeground(new Color(53,32,88));
        c.add(title);
//        new Layout(this.socket);
        layout = new Layout(c,"Overview", socket);
    }

//    public static void main(String args[]) throws IOException {
//        new Overview().init();
//    }
}
