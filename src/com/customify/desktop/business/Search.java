package com.customify.desktop.business;

import javax.swing.*;
import java.awt.*;

public class Search extends JPanel {
    public Search(){
        JPanel search = new JPanel();
        search.setBackground(Color.white);
//        panel2.setPreferredSize(new Dimension(300, 100));
        JTextField searchField = new JTextField("Search");
        searchField.setPreferredSize(new Dimension(300, 25));
        searchField.setFont(new Font("Montserrat", Font.PLAIN, 12));
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(50,100,95,30);
        searchButton.setPreferredSize(new Dimension(80, 24));
        searchButton.setBackground(new Color(53,32,88));
        searchButton.setForeground(Color.white);
        searchButton.setFont(new Font("Montserrat", Font.BOLD, 12));
        searchButton.setBorderPainted(false);
//        searchButton.setPreferredSize(new Dimension(100, 20));
        search.add(searchField);
        search.add(searchButton);
        add(search);
        setBackground(Color.white);
    }
}
