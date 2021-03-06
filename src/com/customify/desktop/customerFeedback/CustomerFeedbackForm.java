package com.customify.desktop.customerFeedback;

import com.customify.desktop.Keys;
import com.customify.desktop.data_formats.Customer_feedback.CustomerFeedbackFormat;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.services.FeedbackServices;
import com.customify.desktop.utils.interfaces.IInputChangedEventListener;
import com.customify.desktop.utils.interfaces.SelectBusinessFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class CustomerFeedbackForm extends Container {
    CustomerFeedbackFormat format = new CustomerFeedbackFormat();

    private final Socket socket;
//    private JPanel contentPane;

    public CustomerFeedbackForm(Socket socket, JFrame closableFrame) throws IOException, ClassNotFoundException {
        this.socket = socket;

        Container contentPane = new Container();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Color.white);
        setLayout(null);

        Businesses b = new Businesses(socket);
        SelectBusinessFormat businessFormat = b.viewAll();
        String[] busList = new String[businessFormat.getNames().size()];
        for(int i = 0; i < busList.length; i++) {
            busList[i] = businessFormat.getNames().get(i);
        }
        JComboBox<String> cb=new JComboBox<>(busList);
        cb.setPreferredSize(new Dimension(100,40));
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                format.setBusinessId(businessFormat.getIds().get(cb.getSelectedIndex()));
            }
        });

        JPanel header = new JPanel();
        JLabel headline = new JLabel("Customer feedback ");
        headline.setPreferredSize(new Dimension(300, 100));
        headline.setFont(new Font("Montserrat", Font.BOLD, 29));
        headline.setForeground(new Color(53,32,88));
        header.setBackground(Color.white);


        JPanel customer_name = createNewInput("Customer name");
        JPanel business_id = createNewInput("Business Id");
        JPanel title = createNewInput("Title");
        JPanel description = createNewInput("Description");

        JPanel buttonGroup = new JPanel();
        buttonGroup.setBackground(Color.white);

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
                newCustomerFeedback();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        buttonGroup.add(btn);

        header.add(headline);

        contentPane.add(header);
        contentPane.add(customer_name);
        contentPane.add(cb);
//        contentPane.add(business_id);
        contentPane.add(title);
        contentPane.add(description);
        contentPane.add(buttonGroup);

        contentPane.setBounds(200, 50, 800, 450);

        add(contentPane);
        setBackground(Color.WHITE);
        new Layout(contentPane,"Send feedback", socket);
    }

    public void newCustomerFeedback() throws IOException, ClassNotFoundException {
        FeedbackServices service = new FeedbackServices(this.socket);
        this.format.setKey(Keys.FEEDBACK);
        service.Feedback(this.format);
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
            switch (placeholderTextParam) {
                case "Customer name":
                    format.setCustomer_name(textField.getText());
                    break;
                case "Title":
                    format.setTitle(textField.getText());
                    break;
                case "Description":
                    format.setDescription(textField.getText());
                    break;
                default:
                    System.out.println("No such label");
            }
        });

        textFieldContainer.add(placeholderText);
        textFieldContainer.add(textField);

        return textFieldContainer;
    }
}