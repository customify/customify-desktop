package com.customify.desktop.profile_settings;

import com.customify.cli.data_format.CreateCustomerFormat;
import com.customify.cli.services.CustomerService;
import com.customify.cli.utils.authorization.UserSession;
import com.customify.desktop.components.buttons_fields.DialogBox;
import com.customify.desktop.components.buttons_fields.RoundJButton;
import com.customify.desktop.components.buttons_fields.RoundJTextField;
import com.customify.desktop.data_formats.user.UpdateUserFormat;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class UserSettings {

    private Socket socket;
    private Socket socket2;
    public UserSettings(){ }


    private Container mainContainer;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public UserSettings(Socket socket, JFrame closableFrame) throws IOException {
        this.socket = socket;
        mainContainer=new Container();
        layout();
    }


    public void layout() throws IOException {

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setSize(screenSize.width,screenSize.height);


        JLabel firstNameLabel = new JLabel("First Name ");
        JLabel lastNameLabel = new JLabel("Last Name ");
        JLabel emailLabel = new JLabel("Email ");


        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);


        UserSession session = new UserSession();
        String json = session.getUserJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        JTextField firstNameField = new RoundJTextField(jsonNode.get("firName").asText(),15);
        firstNameField.setFont(fieldFont);
        JTextField lastNameField = new RoundJTextField(jsonNode.get("lasName").asText(),15);
        lastNameField.setFont(fieldFont);
        JTextField emailField = new RoundJTextField(jsonNode.get("email").asText(),15);
        emailField.setFont(fieldFont);


        firstNameLabel.setFont(new Font("SansSerif",Font.PLAIN,18));
        lastNameLabel.setFont(new Font("SansSerif",Font.PLAIN,18));
        emailLabel.setFont(new Font("SansSerif",Font.PLAIN,18));



        JLabel formTitleLabel = new JLabel("REGISTER NEW CUSTOMER");
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

        JButton button = new RoundJButton("SAVE CHANGES");
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
                updateUser(firstNameField,lastNameField,emailField,jsonNode.get("appUser").asText(),jsonNode.get("id").asText());
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

        mainContainer.add(button);
        mainContainer.add(cancelBtn);
        mainContainer.add(firstNameField);
        mainContainer.add(firstNameLabel);
        mainContainer.add(lastNameField);
        mainContainer.add(lastNameLabel);


        mainContainer.add(emailLabel);
        mainContainer.add(emailField);



        mainContainer.add(formTitleLabel);
        mainContainer.add(panel);
//            mainContainer.setVisible(true);
        new Layout(mainContainer, "List Customers", socket);
    }

    private void updateUser(JTextField firstNameField, JTextField lastNameField, JTextField emailField,String userType,String id) throws IOException, ClassNotFoundException {
        DialogBox dialogBox = new DialogBox();
        if(firstNameField.getText().equals("") || lastNameField.getText().equals("") || emailField.getText().equals(""))
        {
            dialogBox.dialog("CREATE ERROR","Sorry some Boxes are not filled","ERROR");
        }else{
            UpdateUserFormat format = new UpdateUserFormat(id,userType,firstNameField.getText(), lastNameField.getText(),emailField.getText());
            UserService service = new UserService(this.socket);
            int status = service.update(format);
            if(status == 200){
                dialogBox.dialog("UPDATE SUCCESS","PROFILE SETTINGS UPDATED SUCCESSFULLY","SUCCESS");
            }else{
                dialogBox.dialog("INTERNAL SERVER ERROR","SORRY CREATE FAILED TRY AGAIN LATER","ERROR");
            }
        }
    }


}
