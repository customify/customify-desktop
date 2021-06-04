package com.customify.desktop.customer;

import com.customify.cli.data_format.CreateCustomerFormat;
import com.customify.cli.services.CustomerService;
import com.customify.desktop.components.buttons_fields.DialogBox;
import com.customify.desktop.components.buttons_fields.RoundJButton;
import com.customify.desktop.components.buttons_fields.RoundJTextField;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class CreateWindow {

        private Socket socket;
  private Socket socket2;
        public CreateWindow(){ }


        private JFrame mainFrame;
        private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        public CreateWindow(Socket socket){
            this.socket = socket;
            mainFrame = new JFrame("Customify-create-customer");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(screenSize.width,screenSize.height);
            layout();
        }

        public void layout(){

            JPanel panel = new JPanel();
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

            int sharedLabelXBound = 370;
            int sharedFieldXBound = 500;
            int sharedFWidth = 400;
            int defaultMTop = 150;
            int initialAdd = 60;

            formTitleLabel.setBounds(490, 50, 400, 50);

            firstNameLabel.setBounds(sharedLabelXBound, defaultMTop, 200, 25);
            firstNameField.setBounds(sharedFieldXBound, defaultMTop, sharedFWidth, 40);


            lastNameLabel.setBounds(sharedLabelXBound, defaultMTop+initialAdd,200, 20);
            lastNameField.setBounds(sharedFieldXBound, defaultMTop+initialAdd, sharedFWidth, 40);

            emailLabel.setBounds(sharedLabelXBound, defaultMTop+(initialAdd*2),200, 20);
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
            button.setBounds(500, 350, 200, 45);
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
            cancelBtn.setBounds(770, 350, sharedFWidth/3, 44);
            cancelBtn.addActionListener(e -> System.out.println("Cancelled"));

            mainFrame.add(button);
            mainFrame.add(cancelBtn);
            mainFrame.add(firstNameField);
            mainFrame.add(firstNameLabel);
            mainFrame.add(lastNameField);
            mainFrame.add(lastNameLabel);


            mainFrame.add(emailLabel);
            mainFrame.add(emailField);



            mainFrame.add(formTitleLabel);
            mainFrame.add(panel);
            mainFrame.setVisible(true);
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
                    dialogBox.dialog("CREATE SUCCESS","CUSTOMER CREATED SUCCESSFULLY","SUCCESS");
                }else{
                    dialogBox.dialog("INTERNAL SERVER ERROR","SORRY CREATE FAILED TRY AGAIN LATER","ERROR");
                }
            }
        }


    }

