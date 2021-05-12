package com.customify.desktop.business;

import com.customify.desktop.components.FormControl;

import javax.swing.*;
import java.awt.*;

public class NewBusiness extends JPanel {
    public NewBusiness(){
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        setLayout(null);

        JPanel header = new JPanel();
        JLabel headline = new JLabel("New Business ");
        headline.setPreferredSize(new Dimension(300, 100));
        headline.setFont(new Font("Montserrat", Font.BOLD, 29));
        headline.setForeground(new Color(53,32,88));
        header.setBackground(Color.white);

        JPanel businessName = new FormControl("Business name");
        JPanel businessLocation = new FormControl("Location");
        JPanel address = new FormControl("Address");
        JPanel phoneNumber = new FormControl("Phone number");
        JPanel representative = new FormControl("Representative");
        JPanel businessPlan = new FormControl("Business plan");

        JPanel buttonGroup = new JPanel();
        buttonGroup.setBackground(Color.white);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(400,400,180,40);
        cancel.setBackground(Color.white);
        cancel.setBorder(BorderFactory.createCompoundBorder(
                cancel.getBorder(),
                BorderFactory.createEmptyBorder(7, 30, 7, 30)));
        cancel.setFont(new Font("Montserrat", Font.PLAIN, 18));

        JButton btn = new JButton("Register");
        btn.setBounds(1020,400,180,40);
        btn.setBackground(new Color(53,32,88));
        btn.setForeground(Color.white);
        btn.setBorder(BorderFactory.createCompoundBorder(
                btn.getBorder(),
                BorderFactory.createEmptyBorder(7, 30, 7, 30)));
        btn.setFont(new Font("Montserrat", Font.PLAIN, 18));

        buttonGroup.add(btn);
        buttonGroup.add(cancel);

        header.add(headline);

        main.add(header);
        main.add(businessName);
        main.add(businessLocation);
        main.add(address);
        main.add(phoneNumber);
        main.add(representative);
        main.add(businessPlan);
        main.add(buttonGroup);

        main.setBounds(200, 50, 800, 600);

        add(main);
        setBackground(Color.WHITE);
    }

    public void createNewBusiness(){

    }
}
