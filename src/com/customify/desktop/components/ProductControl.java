package com.customify.desktop.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProductControl extends  JPanel {

    public ProductControl(String placeholderTextParam,Integer textFieldHeight){
        setBackground(Color.white);
        setBackground(Color.white);
        JLabel placeholderText = new JLabel(placeholderTextParam);
        placeholderText.setFont(new Font("Montserrat", Font.PLAIN, 18));
        placeholderText.setBackground(Color.green);
        placeholderText.setPreferredSize(new Dimension(200, 30));

        JTextField textField = new JTextField("", 20);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.black, 1, true),
                BorderFactory.createEmptyBorder(8, 15, 8, 15))
        );
        textField.setFont(new Font("Montserrat", Font.PLAIN, 18));
        textField.setBounds(2,10,100,textFieldHeight);
        add(placeholderText);
        add(textField);
    }
}
