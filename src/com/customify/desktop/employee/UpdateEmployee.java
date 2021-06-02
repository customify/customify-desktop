package com.customify.desktop.employee;

import com.customify.desktop.data_formats.business.BusinessFormat;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.utils.interfaces.IInputChangedEventListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class UpdateEmployee extends JPanel {
    public UpdateEmployee() {
//        init();
    }

    public static JPanel init(){

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);

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

        btn.addActionListener(actionEvent -> {
            /*try {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }*/
        });

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

        main.setBounds(90, 40, 800, 600);

        return main;

//        frame.add(main);

//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);
//        frame.setSize(500, 550);
//        frame.setVisible(true);
    }
    public static JPanel createNewInput(String placeholderTextParam){
        JPanel textFieldContainer = new JPanel();
        BusinessFormat format = new BusinessFormat();

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

        textField.getDocument().addDocumentListener((IInputChangedEventListener) e -> {
            switch (placeholderTextParam) {
                case "Business name": format.setName(textField.getText());
                case "Location" : format.setLocation(textField.getText());
                case "Address" : format.setAddress(textField.getText());
                case "Phone number" : format.setPhoneNumber(textField.getText());
                case "Representative" : format.setRepresentative(Integer.parseInt(textField.getText()));
                case "Business plan" : format.setPlan(Integer.parseInt(textField.getText()));
            }
        });

        textFieldContainer.add(placeholderText);
        textFieldContainer.add(textField);

        return textFieldContainer;
    }
    public static void main(String[] args) throws IOException {
        Container container = new Container();
        container.add(UpdateEmployee.init());

        new Layout(container,"Update Employee");
    }
}