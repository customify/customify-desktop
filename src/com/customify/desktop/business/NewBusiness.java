package com.customify.desktop.business;

import com.customify.cli.Keys;
import com.customify.cli.data_format.business.BusinessFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewBusiness extends JPanel {
    BusinessFormat format = new BusinessFormat();

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

        JPanel businessName = createNewInput("Business name");
        JPanel businessLocation = createNewInput("Location");
        JPanel address = createNewInput("Address");
        JPanel phoneNumber = createNewInput("Phone number");
        JPanel representative = createNewInput("Representative");
        JPanel businessPlan = createNewInput("Business plan");

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

        JTextField field = new JTextField("anselme");
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Something has been changed ...");
            }
        });

        main.add(field);
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
        BusinessFormat format = new BusinessFormat(Keys.CREATE_BUSINESS, "Business name", "My location", "No phone", "This address", 1, 3);
    }

    public JPanel createNewInput(String placeholderTextParam){
        JPanel textFieldContainer = new JPanel();
        textFieldContainer.setBackground(Color.white);
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

        textField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(textField.getText());
            }
        });

        textFieldContainer.add(placeholderText);
        textFieldContainer.add(textField);

        return textFieldContainer;
    }
}
