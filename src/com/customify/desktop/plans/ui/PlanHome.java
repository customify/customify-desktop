package com.customify.desktop.plans.ui;

import com.customify.cli.utils.authorization.structure.User;
import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class PlanHome {

    public PlanHome(){}

    Container container = new Container();
    public void init(Socket socket) throws IOException {
        container.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(117, 42, 549, 477);
        container.add(panel);
        panel.setLayout(null);

        JLabel planHome = new JLabel();
        planHome.setText("Plan Panel");
        planHome.setFont(new Font("Montserrat", Font.BOLD,27));
        planHome.setForeground(new Color(53,32,88));
        planHome.setBounds(175, 105, 200, 37);
        panel.add(planHome);

        JButton btnCreatePlan = new JButton("Create Plan");
        btnCreatePlan.setForeground(Color.WHITE);
        btnCreatePlan.setBackground(new Color(53,32,88));
        btnCreatePlan.setBounds(98, 178, 133, 38);
        btnCreatePlan.addActionListener(e -> {
            try {
                PlanRegister planRegister = new PlanRegister();
                planRegister.init(socket);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        panel.add(btnCreatePlan);

        JButton btnReadPlan = new JButton("Read Plan");
        btnReadPlan.setForeground(new Color(53,32,88));
        btnReadPlan.setBackground(Color.WHITE);
        btnReadPlan.setBounds(275, 178, 133, 38);
        btnReadPlan.addActionListener(e -> {
            try {
                PlanRegister planRegister = new PlanRegister();
                planRegister.init(socket);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        panel.add(btnReadPlan);

        JButton btnFeatureAssign = new JButton("Feature Assign");
        btnFeatureAssign.setForeground(new Color(53,32,88));
        btnFeatureAssign.setBackground(Color.WHITE);
        btnFeatureAssign.setBounds(98, 251, 133, 38);
        btnFeatureAssign.addActionListener(e -> {
            try {
                PlanAssign planAssign = new PlanAssign();
                planAssign.init(socket);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        panel.add(btnFeatureAssign);

        JButton btnCustomerAssign = new JButton("Customer Assign");
        btnCustomerAssign.setForeground(Color.WHITE);
        btnCustomerAssign.setBackground(new Color(53,32,88));
        btnCustomerAssign.setBounds(275, 251, 133, 38);
        btnCustomerAssign.addActionListener(e -> {
            try {
                UserAssign userAssign = new UserAssign();
                userAssign.init(socket);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        panel.add(btnCustomerAssign);

        JButton btnDeletePlan = new JButton("Delete Plan");
        btnDeletePlan.setForeground(Color.WHITE);
        btnDeletePlan.setBackground(new Color(53,32,88));
        btnDeletePlan.setBounds(98, 318, 133, 38);
        panel.add(btnDeletePlan);

        JButton btnUpdatePlan = new JButton("Update Plan");
        btnUpdatePlan.setForeground(new Color(53,32,88));
        btnUpdatePlan.setBackground(Color.WHITE);
        btnUpdatePlan.setBounds(275, 318, 133, 38);
        panel.add(btnUpdatePlan);

        new Layout(container, "Plan Home", socket);
    }
}
