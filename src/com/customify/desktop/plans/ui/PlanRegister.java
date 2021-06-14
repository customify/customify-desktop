package com.customify.desktop.plans.ui;

import com.customify.desktop.layout.Layout;
import com.customify.desktop.plans.model.Plans;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class PlanRegister {
    private JTextField title;
    private JTextField description;
    private JTextField price;

    Container container = new Container();


    public PlanRegister(){}
    public void init(Socket socket) throws IOException {
        container.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(117, 42, 589, 514);
        container.add(panel);
        panel.setLayout(null);

        JTextArea PlanRegister = new JTextArea();
        PlanRegister.setText("Plan Register");
        PlanRegister.setForeground(new Color(53,32,88));
        PlanRegister.setFont(new Font("Montserrat", Font.BOLD,27));
        PlanRegister.setBounds(185, 105, 193, 37);
        panel.add(PlanRegister);

        JLabel lblPlanTitle = new JLabel("Plan Title");
        lblPlanTitle.setBounds(88, 197, 156, 30);
        lblPlanTitle.setFont(new Font("Montserrat", Font.BOLD,15));
        panel.add(lblPlanTitle);

        JLabel lblPlanDescription = new JLabel("Plan Description");
        lblPlanDescription.setBounds(88, 249, 156, 30);
        lblPlanDescription.setFont(new Font("Montserrat", Font.BOLD,15));
        panel.add(lblPlanDescription);

        JLabel lblPlanPrice = new JLabel("Price");
        lblPlanPrice.setBounds(88, 306, 156, 30);
        lblPlanPrice.setFont(new Font("Montserrat", Font.BOLD,15));
        panel.add(lblPlanPrice);

        title = new JTextField();
        title.setBounds(264, 197, 186, 30);
        panel.add(title);
        title.setColumns(10);

        description = new JTextField();
        description.setColumns(10);
        description.setBounds(264, 249, 186, 30);
        panel.add(description);

        price = new JTextField();
        price.setColumns(10);
        price.setBounds(264, 306, 186, 30);
        panel.add(price);

        JButton cancel = new JButton("Cancel");
        cancel.setActionCommand("");
        cancel.setBounds(132, 400, 106, 30);
        cancel.setBackground(Color.WHITE);
        cancel.addActionListener(e -> {
            try {
                PlanHome planHome = new PlanHome();
                planHome.init(socket);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        panel.add(cancel);

        JButton save = new JButton("Save");
        save.setForeground(Color.WHITE);
        save.setBackground(new Color(53,32,88));
        save.setBounds(264, 400, 106, 30);
        save.addActionListener(e ->{
            try{
                Plans plans = new Plans(title.getText(),description.getText(),Integer.parseInt(price.getText()));
                PlanService planService = new PlanService(socket, plans);
                planService.create();
            }catch (Exception exception){
                exception.printStackTrace();
            }
        });
        panel.add(save);


        new Layout(container, "Plan register", socket);

    }

}
