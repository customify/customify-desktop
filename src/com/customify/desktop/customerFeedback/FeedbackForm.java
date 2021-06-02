package com.customify.desktop.components.customerFeedback;

import com.customify.server.Db.Db;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * User Registration using Swing
 * @author javaguides.net
 *
 */
public class FeedbackForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField customer_name;
    private JTextField business_id;
    private JTextField title;
    private JTextField description;
    private JButton btnNewButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FeedbackForm frame = new FeedbackForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public FeedbackForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel feedbackLabel = new JLabel("Provide feedback");
        feedbackLabel.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        feedbackLabel.setBounds(362, 52, 325, 50);
        contentPane.add(feedbackLabel);


        JLabel lbl_customerName = new JLabel("Names");
        lbl_customerName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_customerName.setBounds(58, 152, 99, 43);
        contentPane.add(lbl_customerName);


        JLabel lbl_businessId = new JLabel("Business\r\n Id");
        lbl_businessId.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_businessId.setBounds(58, 243, 110, 29);
        contentPane.add(lbl_businessId);

        customer_name = new JTextField();
        customer_name.setFont(new Font("Tahoma", Font.PLAIN, 32));
        customer_name.setBounds(214, 151, 228, 50);
        contentPane.add(customer_name);
        customer_name.setColumns(10);

        business_id = new JTextField();
        business_id.setFont(new Font("Tahoma", Font.PLAIN, 32));
        business_id.setBounds(214, 235, 228, 50);
        contentPane.add(business_id);
        business_id.setColumns(10);


        JLabel lbl_title = new JLabel("Title");
        lbl_title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_title.setBounds(542, 159, 99, 29);
        contentPane.add(lbl_title);


        JLabel lbl_description = new JLabel("Comment");
        lbl_description.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_description.setBounds(542, 245, 99, 24);
        contentPane.add(lbl_description);

        title = new JTextField();
        title.setFont(new Font("Tahoma", Font.PLAIN, 32));
        title.setBounds(707, 151, 228, 50);
        contentPane.add(title);
        title.setColumns(10);


        description = new JTextField();
        description.setFont(new Font("Tahoma", Font.PLAIN, 32));
        description.setBounds(707, 235, 228, 50);
        contentPane.add(description);
        description.setColumns(10);

        btnNewButton = new JButton("Send");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String names = customer_name.getText();
                String businessId = business_id.getText();
                String feedbackTitle = title.getText();
                String feedbackDescription = description.getText();

                String msg = "" + names;
                msg += " \n";
                if (businessId == "null") {
                    JOptionPane.showMessageDialog(btnNewButton, "Business id is necessary");
                }

                try {
//                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "root");
//
//                    String query = "INSERT INTO account values('" + firstName + "','" + lastName + "','" + userName + "','" +
//                            password + "','" + emailId + "','" + mobileNumber + "')";


                    Connection connection = Db.getConnection();
                    String query = "INSERT INTO CustomerFeedback values('" + names + "','" + businessId + "','" + feedbackTitle +"','"+feedbackDescription+"')";

                    Statement sta = connection.createStatement();
                        int x = sta.executeUpdate(query);
                        if (x == 0) {
                            JOptionPane.showMessageDialog(btnNewButton, "This is already exist");
                        } else {
                            JOptionPane.showMessageDialog(btnNewButton,
                                    "Feedback successfully sent");
                        }
                        connection.close();

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton.setBounds(399, 447, 259, 74);
        contentPane.add(btnNewButton);
    }
}