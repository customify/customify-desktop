package com.customify.desktop.components.plan;


import com.customify.desktop.layout.Layout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
public class PlanRegister {
    Container container = new Container();

    public Container init(){

        JLabel title = new JLabel("Register Plan");
        title.setFont(new Font("Montserrat", Font.BOLD,30));
        title.setBounds(400,20,300,100);
        title.setForeground(new Color(53,32,88));
        container.add(title);
        JLabel label1 = new JLabel("Plan Name");
        label1.setFont(new Font("Montserrat", Font.BOLD, 20));

        JLabel label2 = new JLabel("Plan Description");
        label2.setFont(new Font("Montserrat", Font.BOLD, 20));

        JLabel label3 = new JLabel("Price");
        label3.setFont(new Font("Montserrat", Font.BOLD, 20));

        JLabel label4 = new JLabel("Select Feature");
        label4.setFont(new Font("Montserrat", Font.BOLD, 20));

        label1.setBounds(200,200,200,20);
        label2.setBounds(200,250,200,20);
        label3.setBounds(200,300,200,20);
        label4.setBounds(200,350,200,20);

        JTextField planTitle  = new JTextField();
        planTitle.setBounds(400,200,400,40);
        container.add(planTitle);

        JTextField planDescription  = new JTextField();
        planDescription.setBounds(400,250,400,40);
        container.add(planDescription);

        JTextField planPrice  = new JTextField();
        planPrice.setBounds(400,300,400,40);
        container.add(planPrice);

        JCheckBox checkBox1 = new JCheckBox();
        checkBox1.setText("Shop");

        JCheckBox checkBox2 = new JCheckBox();
        checkBox2.setText("Order");

        CheckboxGroup checkBoxGroup = new CheckboxGroup();
//        here to put the loop for creating multiple checkbox according to features in database


//        checkBoxGroup.add(checkBox1);
//        checkBoxGroup.add(checkBox2);
        container.add(checkBox1);
        container.add(checkBox2);


        container.add(label1);
        container.add(label2);
        container.add(label3);
        container.add(label4);


        return container;
    }

    public static void main(String[] args) throws IOException {
        new Layout((new PlanRegister().init()));
    }

}
