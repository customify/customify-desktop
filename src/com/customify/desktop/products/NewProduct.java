package com.customify.desktop.products;

import com.customify.desktop.components.FormControl;
import com.customify.desktop.components.ProductControl;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class NewProduct extends JPanel {

    public NewProduct(){
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        setLayout(null);

        JPanel main2=new JPanel();
        main2.setLayout(new GridLayout(4,2));
        main2.setBackground(Color.white);
        JPanel header = new JPanel();
        header.setSize(new Dimension(500,300));
        JLabel headline = new JLabel("ADD PRODUCT ");
        //headline.setPreferredSize(new Dimension());
        headline.setFont(new Font("Montserrat", Font.BOLD, 29));
        headline.setForeground(new Color(53,32,88));
        headline.setBounds(-400, 100,800,40);
        header.setBackground(Color.white);

        JPanel productName = new ProductControl("Product name",200);
        JPanel productDescription = new ProductControl("Product Description",300);
        JPanel quantity = new ProductControl("Quantity",200);
        JPanel points = new ProductControl("Points",200);
        JPanel price = new ProductControl("Price",200);

        JPanel buttonGroup = new JPanel();
        buttonGroup.setBackground(Color.white);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(400,400,180,40);
        cancel.setBackground(Color.white);
        cancel.setBorder(BorderFactory.createCompoundBorder(
                cancel.getBorder(),
                BorderFactory.createEmptyBorder(7, 30, 7, 30)));
        cancel.setFont(new Font("Montserrat", Font.PLAIN, 18));


        JButton btn = new JButton("ADD");
        btn.setBounds(1020,400,180,40);
        btn.setBackground(new Color(53,32,88));
        btn.setForeground(Color.white);
        btn.setBorder(BorderFactory.createCompoundBorder(
                btn.getBorder(),
                BorderFactory.createEmptyBorder(7, 30, 7, 30)));
        btn.setFont(new Font("Montserrat", Font.PLAIN, 18));

        buttonGroup.add(btn);
        buttonGroup.add(cancel);

        header.add(headline);

        main.add(header);
        main2.add(productName);
        main2.add(quantity);
        main2.add(productDescription);
        main2.add(points);
        main2.add(price);
        main.add(main2);
        main.add(buttonGroup);


        main.setBounds(90, 50, 800, 700);

        add(main);
        setBackground(Color.white);
    }

}