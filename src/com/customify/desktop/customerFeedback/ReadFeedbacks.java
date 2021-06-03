package com.customify.desktop.customerFeedback;

import com.customify.desktop.data_formats.Customer_feedback.CustomerFeedbackFormat;
import com.customify.desktop.utils.interfaces.SelectBusinessFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;


public class ReadFeedbacks extends JPanel{
    CustomerFeedbackFormat format = new CustomerFeedbackFormat();
    private final Socket socket;


    public ReadFeedbacks(Socket socket)throws IOException, ClassNotFoundException{
        this.socket = socket;

        JPanel contentPane = new JPanel();
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
        cb.setPreferredSize(new Dimension(200,40));
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


        header.add(headline);

        contentPane.add(cb);


        contentPane.setBounds(200, 50, 800, 400);
        add(contentPane);
        setBackground(Color.WHITE);
    }
}
