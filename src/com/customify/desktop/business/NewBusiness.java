package com.customify.desktop.business;

import com.customify.desktop.Keys;
import com.customify.desktop.data_formats.business.BusinessFormat;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.services.BusinessService;
import com.customify.desktop.utils.interfaces.IInputChangedEventListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class NewBusiness extends JPanel {
    BusinessFormat format = new BusinessFormat();

    private final Socket socket;

    public NewBusiness(Socket socket){
        this.socket = socket;

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
//        JPanel selectAString = createNewSelect("Business Plan");

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
            try {
                createNewBusiness();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
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
//        main.add(selectAString);
        main.add(buttonGroup);

        main.setBounds(200, 50, 800, 600);

        add(main);
        setBackground(Color.WHITE);
    }

    public void createNewBusiness() throws IOException, ClassNotFoundException {
        BusinessService service = new BusinessService(this.socket);
        this.format.setKey(Keys.CREATE_BUSINESS);
        service.create(this.format);
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

        textField.getDocument().addDocumentListener((IInputChangedEventListener) e -> {
            System.out.println(textField.getText() + placeholderTextParam);
            switch (placeholderTextParam) {
                case "Business name":
                    format.setName(textField.getText());
                    break;
                case "Location" :
                    format.setLocation(textField.getText());
                    break;
                case "Address" :
                    format.setAddress(textField.getText());
                    break;
                case "Phone number" :
                    format.setPhoneNumber(textField.getText());
                    break;
                case "Representative" :
                    format.setRepresentative(Integer.parseInt(textField.getText()));
                    break;
                case "Business plan" :
                    format.setPlan(Integer.parseInt(textField.getText()));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + placeholderTextParam);
            }
        });

        textFieldContainer.add(placeholderText);
        textFieldContainer.add(textField);

        return textFieldContainer;
    }

    public static void main(String[] args) throws IOException {
        new Layout(new NewBusiness(new Socket()), "testing....");
    }

    public JPanel createNewSelect(String placeholderTextParam){
        JPanel textFieldContainer = new JPanel();
        textFieldContainer.setBackground(Color.white);

        JLabel placeholderText = new JLabel(placeholderTextParam);
        placeholderText.setFont(new Font("Montserrat", Font.PLAIN, 18));
        placeholderText.setBackground(Color.green);
        placeholderText.setPreferredSize(new Dimension(200, 30));



        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
        JComboBox<String> petList = new JComboBox<>(petStrings);

        petList.setFont(new Font("Montserrat", Font.PLAIN, 18));

        petList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println(petList.getSelectedIndex());
            }
        });

        textFieldContainer.add(placeholderText);
        textFieldContainer.add(petList);

        return textFieldContainer;
    }
}
