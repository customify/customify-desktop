package com.customify.desktop.employee;
import com.customify.cli.data_format.CreateCustomerFormat;
import com.customify.cli.services.CustomerService;
import com.customify.desktop.components.buttons_fields.DialogBox;
import com.customify.desktop.components.buttons_fields.RoundJButton;
import com.customify.desktop.components.buttons_fields.RoundJTextField;
import com.customify.desktop.layout.Layout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;


public class createEmployee {
    private Socket socket;
    private Socket socket2;
    public createEmployee(){ }
    private JFrame mainFrame;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public createEmployee(Socket socket){
        this.socket = socket;
//        mainFrame = new JFrame("Customify-create-employee");
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrame.setSize(screenSize.width,screenSize.height);
//        layout();
    }
    public JPanel layout(){
        JPanel panel = new JPanel();
        JPanel mainPanel = new JPanel();

        panel.setBackground(Color.WHITE);
        panel.setSize(screenSize.width,screenSize.height);

        JLabel firstNameLabel = new JLabel("First Name ");
        JLabel lastNameLabel = new JLabel("Last Name ");
        JLabel emailLabel = new JLabel("Email ");

        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);
        JTextField firstNameField = new RoundJTextField(15);
        firstNameField.setFont(fieldFont);

        JTextField lastNameField = new RoundJTextField(15);
        lastNameField.setFont(fieldFont);


        JTextField emailField = new RoundJTextField(15);
        emailField.setFont(fieldFont);

        firstNameLabel.setFont(new Font("SansSerif",Font.PLAIN,18));
        lastNameLabel.setFont(new Font("SansSerif",Font.PLAIN,18));
        emailLabel.setFont(new Font("SansSerif",Font.PLAIN,18));

        JLabel formTitleLabel = new JLabel("REGISTER NEW EMPLOYEE");
        formTitleLabel.setFont(new Font("SansSerif",Font.BOLD,25));

        int sharedLabelXBound = 37;
        int sharedFieldXBound = 50;
        int sharedFWidth = 40;
        int defaultMTop = 15;
        int initialAdd = 60;
        formTitleLabel.setBounds(40, 50, 40, 50);
        firstNameLabel.setBounds(sharedLabelXBound, defaultMTop, 20, 25);
        firstNameField.setBounds(sharedFieldXBound, defaultMTop, sharedFWidth, 40);
        lastNameLabel.setBounds(sharedLabelXBound, defaultMTop+initialAdd,20, 20);
        lastNameField.setBounds(sharedFieldXBound, defaultMTop+initialAdd, sharedFWidth, 40);
        emailLabel.setBounds(sharedLabelXBound, defaultMTop+(initialAdd*2),20, 20);
        emailField.setBounds(sharedFieldXBound, defaultMTop+(initialAdd*2), sharedFWidth, 40);
        Font btnFont = new Font("SansSerif", Font.PLAIN, 16);
        JButton button = new RoundJButton("Register");
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0X352058));
        button.setFont(btnFont);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(10, 30, 10, 30);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.setBounds(50, 35, 20, 24);
        button.addActionListener(e -> {
            try {
                registerCustomer(firstNameField,lastNameField,emailField);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        JButton cancelBtn = new RoundJButton("Cancel");
        cancelBtn.setForeground(new Color(0X352058));
        cancelBtn.setBackground(Color.WHITE);
        cancelBtn.setFont(btnFont);
        cancelBtn.setBorder(compound);
        cancelBtn.setBounds(70, 30, sharedFWidth/3, 24);
        cancelBtn.addActionListener(e -> System.out.println("Cancelled"));

        mainPanel.add(button);
        mainPanel.add(cancelBtn);
        mainPanel.add(firstNameField);
        mainPanel.add(firstNameLabel);
        mainPanel.add(lastNameField);
        mainPanel.add(lastNameLabel);
        mainPanel.add(emailLabel);
        mainPanel.add(emailField);
        mainPanel.add(formTitleLabel);
//        mainPanel.add(panel);
//        mainPanel.setVisible(true);

        return mainPanel;
    }

    private void registerCustomer(JTextField firstNameField, JTextField lastNameField, JTextField emailField) throws IOException, ClassNotFoundException {
        DialogBox dialogBox = new DialogBox();
        if(firstNameField.getText().equals("") || lastNameField.getText().equals("") || emailField.getText().equals(""))
        {
            dialogBox.dialog("CREATE ERROR","Sorry some Boxes are not filled","ERROR");
        }else{
            CreateCustomerFormat format = new CreateCustomerFormat(emailField.getText(), lastNameField.getText(), firstNameField.getText());
            CustomerService customerService = new CustomerService(this.socket);
            int status = customerService.create(format);
            if(status == 201){
                dialogBox.dialog("CREATE SUCCESS","EMPLOYEE CREATED SUCCESSFULLY","SUCCESS");
            }else{
                dialogBox.dialog("INTERNAL SERVER ERROR","SORRY CREATE FAILED TRY AGAIN LATER","ERROR");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Container container = new Container();
        container.add(new createEmployee().layout());

        new Layout(container,"Create Employee");

    }

//    private static Component init() {
//        return null;
//    }

}

