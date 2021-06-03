package com.customify.desktop.business;

import com.customify.desktop.Keys;
import com.customify.desktop.data_formats.business.BusinessFormat;
import com.customify.desktop.services.BusinessService;
import com.customify.desktop.utils.interfaces.IInputChangedEventListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class NewBusiness extends JPanel {
    BusinessFormat format = new BusinessFormat();

    private final Socket socket;

    public NewBusiness(Socket socket) {
        this.socket = socket;

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        setLayout(null);

        JPanel header = new JPanel();
        JLabel headline = new JLabel("New Business ");
        headline.setPreferredSize(new Dimension(300, 100));
        headline.setFont(new Font("Montserrat", Font.BOLD, 29));
        headline.setForeground(new Color(53, 32, 88));
        header.setBackground(Color.white);

        JLabel response = new JLabel("");
        response.setPreferredSize(new Dimension(300, 50));
        response.setFont(new Font("Montserrat", Font.PLAIN, 19));
        response.setForeground(Color.green);

        JPanel businessName = createNewInput("Business name");
        JPanel businessLocation = createNewInput("Location");
        JPanel address = createNewInput("Address");
        JPanel phoneNumber = createNewInput("Phone number");
        JPanel businessPlan = createNewSelect("Business Plan");

        JPanel buttonGroup = new JPanel();
        buttonGroup.setBackground(Color.white);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(400, 400, 180, 40);
        cancel.setBackground(Color.white);
        cancel.setBorder(BorderFactory.createCompoundBorder(
                cancel.getBorder(),
                BorderFactory.createEmptyBorder(7, 30, 7, 30)));
        cancel.setFont(new Font("Montserrat", Font.PLAIN, 18));

        JButton btn = new JButton("Register");
        btn.setBounds(1020, 400, 180, 40);
        btn.setBackground(new Color(53, 32, 88));
        btn.setForeground(Color.white);
        btn.setBorder(BorderFactory.createCompoundBorder(
                btn.getBorder(),
                BorderFactory.createEmptyBorder(7, 30, 7, 30)));
        btn.setFont(new Font("Montserrat", Font.PLAIN, 18));

        btn.addActionListener(actionEvent -> {

            try {
                if (createNewBusiness() == 1) response.setText("Successfully created the business");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        buttonGroup.add(btn);
        buttonGroup.add(cancel);

        header.add(headline);

        main.add(header);
        main.add(response);
        main.add(businessName);
        main.add(businessLocation);
        main.add(address);
        main.add(phoneNumber);
        main.add(businessPlan);
        main.add(buttonGroup);

        main.setBounds(200, 50, 800, 600);

        add(main);
        setBackground(Color.WHITE);
    }

    public int createNewBusiness() throws IOException, ClassNotFoundException {
        BusinessService service = new BusinessService(this.socket);
        this.format.setKey(Keys.CREATE_BUSINESS);
        this.format.setRepresentative(1);
        return service.create(this.format);
    }

    public JPanel createNewSelect(String placeholderTextParam) {
        format.setPlan(1);
        String[] strings = {"Basic", "Classic updated", "Plan updated"};
        JPanel container = new JPanel();

        container.setBackground(Color.white);

        JLabel label = new JLabel(placeholderTextParam);
        label.setPreferredSize(new Dimension(200, 30));
        label.setFont(new Font("Montserrat", Font.PLAIN, 18));

        JComboBox<String> comboBox = new JComboBox<>(strings);
        comboBox.setPreferredSize(new Dimension(370, 40));
        comboBox.addActionListener(actionEvent -> format.setPlan(comboBox.getSelectedIndex() + 1));

        container.add(label);
        container.add(comboBox);

        return container;
    }

    public JPanel createNewInput(String placeholderTextParam) {
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

        textField.getDocument().addDocumentListener((IInputChangedEventListener) e -> {
            switch (placeholderTextParam) {
                case "Business name" -> format.setName(textField.getText());
                case "Location" -> format.setLocation(textField.getText());
                case "Address" -> format.setAddress(textField.getText());
                case "Phone number" -> format.setPhoneNumber(textField.getText());
            }
        });

        textFieldContainer.add(placeholderText);
        textFieldContainer.add(textField);

        return textFieldContainer;
    }
}
