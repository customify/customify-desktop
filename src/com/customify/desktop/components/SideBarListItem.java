package com.customify.desktop.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SideBarListItem extends JButton {
    public SideBarListItem(String imagePath, String name) throws IOException {
        JLabel label = new JLabel();
        label.setText(name);
        label.setForeground(new Color(164, 166, 179));
        label.setFont(new Font("Montserrat", Font.PLAIN, 18));
        label.setSize(20, 40);
        label.setBackground(Color.red);

        BufferedImage myPicture = ImageIO.read(new File(System.getProperty("user.dir") + "/src/com/customify/desktop/assets/" + imagePath));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBorder(new EmptyBorder(0, 0, 0, 30));
        add(picLabel);

        setBounds(40, 160, 220, 40);
        setBackground(new Color(53, 32, 88));
        setLayout(new GridLayout(1,2));
        add(label);
        setBorderPainted(false);
    }
}
