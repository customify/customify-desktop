package com.customify.desktop.layout;

import com.customify.desktop.components.SideBarListItem;
import com.customify.desktop.components.Sidebar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Layout  {
    public Layout(Container body) throws IOException {
        JFrame frame = new JFrame("Layout");
        frame.setBackground(Color.white);
        JPanel sidebar = new Sidebar();
        JPanel navbar = new JPanel();


        navbar.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//        JButton profile = new SideBarListItem("profile.png","");
//        profile.setSize(20, 20);
//        profile.setMaximumSize(profile.getSize());
//        navbar.add(profile);

        JLabel name = new JLabel("Gisa Kaze Fredson");;
        name.setForeground(Color.white);
        name.setVerticalAlignment(SwingConstants.CENTER);
        name.setSize(150,100);
        name.setFont(name.getFont().deriveFont(16.0f));

        navbar.add(name,gbc);

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
