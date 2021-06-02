package com.customify.desktop.layout;

import com.customify.desktop.components.SideBarListItem;
import com.customify.desktop.components.Sidebar;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Layout  {
    public Layout(Container body) throws IOException {
        JFrame frame = new JFrame("Layout");
        frame.setBackground(Color.white);
        JPanel sidebar = new Sidebar();
        JPanel navbar = new JPanel();

        /*   header starts */
        
        navbar.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "/src/com/customify/desktop/assets/profile.png"));

        int diameter = Math.min(img.getWidth(), img.getHeight());
        BufferedImage mask = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = mask.createGraphics();
//        applyQualityRenderingHints(g2d);
        g2d.fillOval(0, 0, diameter - 1, diameter - 1);
        g2d.dispose();

        BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        g2d = masked.createGraphics();
//        applyQualityRenderingHints(g2d);
        int x = (diameter - img.getWidth()) / 2;
        int y = (diameter - img.getHeight()) / 2;
        g2d.drawImage(img, x, y, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
        g2d.drawImage(mask, 0, 0, null);
        g2d.dispose();
        
        ImageIcon icon = new ImageIcon(masked.getScaledInstance(30,30,BufferedImage.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel();
        imageLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        imageLabel.setIcon(icon);
        
        

        navbar.add(imageLabel);

        JLabel name = new JLabel("Gisa Kaze Fredson");;
        name.setForeground(Color.white);
        name.setFont(name.getFont().deriveFont(16.0f));

        navbar.add(name,gbc);

        navbar.setBackground(new Color(53,32,88));
        navbar.setBounds(300, 0, 1620, 70);

        /*Header ends here*/

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
