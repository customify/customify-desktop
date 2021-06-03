package com.customify.desktop;

import com.customify.cli.data_format.AuthenticationDataFormat;
import com.customify.cli.services.AuthService;
import com.customify.cli.*;
import com.customify.desktop.components.Overview;
import com.customify.desktop.components.Sidebar;
import com.customify.desktop.components.buttons_fields.DialogBox;
import com.customify.desktop.components.buttons_fields.RoundJButton;
import com.customify.desktop.components.buttons_fields.RoundJPasswordField;
import com.customify.desktop.components.buttons_fields.RoundJTextField;
import com.customify.desktop.utils.RouteWindow;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.Socket;

public class LoginWindow {
    private JFrame mainFrame;
    private Socket socket;
    private Socket socket1;


    public LoginWindow(Socket socket) {
        this.socket = socket;
        mainFrame = new JFrame("Customify-Sign-in");

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenSize.width,screenSize.height);
        mainFrame.setMinimumSize(new Dimension(1800,1800));
        layout();
    }


    public void layout(){
        JPanel panel = new JPanel();
        BorderLayout bdl = new BorderLayout();
        bdl.setHgap(0);
        bdl.setVgap(0);

        panel.setBackground(new Color(0X352058));

        panel.setBounds(0,0,500,1000);

        JPanel panel2 = new JPanel();
        panel2.setBounds(500,0,500,1000);
        panel2.setBackground(Color.WHITE);
        JPanel panelImage = new JPanel();
        panelImage.setBounds(100,200,200,200);
        panelImage.add(profileIcon());
        mainFrame.add(panelImage);
        mainFrame.add(title());
        form();
        mainFrame.add(panel);
        mainFrame.add(panel2);

        mainFrame.setVisible(true);
    }

    public JLabel title(){
        JLabel label = new JLabel();
        label.setText("Welcome to Customify!");
        label.setForeground(Color.white);
        label.setFont(new Font("SansSerif",Font.PLAIN,20));
        label.setBounds(100,350,300,200);
        return label;
    }



    public JLabel profileIcon(){
        ImageIcon image = new ImageIcon("src/com/customify/desktop/assets/profileIcon.png");
        JLabel label = new JLabel();
        label.setIcon(image);
        return label;
    }


    public void form() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300,400));
        JTextField textField = new RoundJTextField(15);
        JPasswordField jPasswordField = new RoundJPasswordField(15);
        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        label.setFont(new Font("SansSerif",Font.BOLD,20));
        label2.setFont(new Font("SansSerif",Font.BOLD,20));
        Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);
        textField.setFont(fieldFont);
        jPasswordField.setFont(fieldFont);

        JLabel titleLabel = new JLabel("Sign in");
        titleLabel.setFont(new Font("SansSerif",Font.BOLD,25));

        titleLabel.setBounds(800, 90, 200, 50);

        label.setText("Email:");
        label2.setText("Password:");
        label.setBounds(600, 200, 200, 20);
        textField.setBounds(600, 250, 420, 40);

        label2.setBounds(600, 300,200, 20);
        jPasswordField.setBounds(600, 350, 420, 40);


        Font btnFont = new Font("SansSerif", Font.PLAIN, 16);

        JButton button = new RoundJButton("LOGIN");
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0X352058));
        button.setFont(btnFont);
        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(10, 30, 10, 30);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.setBounds(700, 410, 200, 50);
        button.addActionListener(e -> {
            try {
                handleLogin(textField,jPasswordField);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        mainFrame.add(titleLabel);
        mainFrame.add(label);
        mainFrame.add(label2);
        mainFrame.add(button);
        mainFrame.add(textField);
        mainFrame.add(jPasswordField);
    }


    public void handleLogin(JTextField textField,JPasswordField passwordField) throws Exception {

        System.out.println("Label 1 "+textField.getText());
        System.out.println("Label 2 "+ String.valueOf(passwordField.getPassword()));

        DialogBox dialogBox = new DialogBox();
        if(textField.getText().equals("") || String.valueOf(passwordField.getPassword()).equals(""))
        {
            dialogBox.dialog("AUTH ERROR","Please fill in all boxes","ERROR");
        }else {
            AuthenticationDataFormat format = new AuthenticationDataFormat(textField.getText() , String.valueOf(passwordField.getPassword()));
            AuthService authService = new AuthService(this.socket, format);
            if (authService.authenticate()) {
                dialogBox.dialog("AUTH RESPONSE","Logged In Successfully","SUCCESS");
//                RouteWindow route = new RouteWindow(authService.getLoggedInUser(),this.socket);
                  mainFrame.dispose();
                  new Sidebar(this.socket);
                  new Overview().init();
            } else {

                dialogBox.dialog("AUTH ERROR","SORRY YOU HAVE WRONG CREDENTIALS","ERROR");
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
