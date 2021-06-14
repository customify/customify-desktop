package com.customify.desktop.plans.ui;

import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class PlanAssign {
    Container container = new Container();
    public PlanAssign() {}

    public void init(Socket socket) throws IOException {
        container.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(117, 42, 549, 477);
        container.add(panel);
        panel.setLayout(null);

        JLabel planAssign = new JLabel();
        planAssign.setText("Assign Feature");
        planAssign.setFont(new Font("Montserrat", Font.BOLD,27));
        planAssign.setForeground(new Color(53,32,88));
        planAssign.setBounds(175, 105, 200, 37);
        panel.add(planAssign);

        JLabel lblPlanId = new JLabel("Plan Id");
        lblPlanId.setBounds(88, 197, 156, 30);
        panel.add(lblPlanId);

        JLabel lblFeatureId = new JLabel("Feature Id");
        lblFeatureId.setBounds(88, 249, 156, 30);
        panel.add(lblFeatureId);

        JTextField planId = new JTextField();
        planId.setBounds(264, 197, 186, 30);
        panel.add(planId);
        planId.setColumns(10);

        JTextField featureId = new JTextField();
        featureId.setColumns(10);
        featureId.setBounds(264, 254, 186, 30);
        panel.add(featureId);

        JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.setActionCommand("");
        cancel.setBounds(117, 324, 106, 30);
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
        save.setBackground(new Color(53,32,88));
        save.setForeground(Color.WHITE);
        save.setBounds(251, 324, 106, 30);
        panel.add(save);

        new Layout(container, "Plan Assign", socket);
    }
}
