package com.customify.client.components;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {
    public Sidebar(){
        this.setBackground(new Color(53,32,88));
        this.setBounds(0,0,300, 1080);
        this.setLayout(null);

        JPanel logo = this.logo();
        this.add(logo);
    }

    public JPanel logo(){
        JPanel myLogo = new JPanel();
        myLogo.setBackground(new Color(53,32,88));
        myLogo.setBounds(53, 81, 200, 100);

        JLabel logoName = new JLabel("Customify");
        logoName.setFont(new Font("Montserrat", Font.BOLD, 29));
        logoName.setForeground(new Color(164, 166, 179));

        myLogo.add(logoName);
        return myLogo;
    }
}
