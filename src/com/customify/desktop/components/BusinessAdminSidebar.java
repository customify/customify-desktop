package com.customify.desktop.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import com.customify.desktop.product.Products;
import com.customify.desktop.sales.Sales;

public class BusinessAdminSidebar extends JPanel {

    public BusinessAdminSidebar() throws IOException {
        setBackground(new Color(53, 32, 88));
        setBounds(0, 0, 300, 1080);
        setLayout(null);

        JPanel navBarItems = new JPanel();
        navBarItems.setLayout(new BoxLayout(navBarItems, BoxLayout.Y_AXIS));
        navBarItems.setBounds(0, 180, 250, 500);
        navBarItems.setBackground(new Color(53, 32, 88));

        JButton overView = new SideBarListItem("Overview.png", "Overview");
        JButton sales = new SideBarListItem("4. contacts.png", "Sales");
        JButton employees = new SideBarListItem("4. contacts.png", "Employees");
        JButton customers = new SideBarListItem("4. contacts.png", "Customers");
        JButton report = new SideBarListItem("4. contacts.png", "Report");
        JButton feedback = new SideBarListItem("4. contacts.png", "Feedback");
        JButton settings = new SideBarListItem("4. contacts.png", "Settings");
        JButton subscription = new SideBarListItem("4. contacts.png", "Subscription");
        JButton products=new SideBarListItem("4. contacts.png", "Products");

        navBarItems.add(overView);
        navBarItems.add(employees);
        navBarItems.add(customers);
        navBarItems.add(sales);
        navBarItems.add(products);
        navBarItems.add(settings);

        JPanel line = new JPanel();
        line.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(164, 166, 179)));
        line.setSize(100, 10);
        line.setBackground(new Color(53, 32, 88));


        ActionListener productAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Products();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        };

        ActionListener salesAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Sales();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };

        sales.addActionListener(salesAction);
        products.addActionListener(productAction);

        JPanel logo = logo();

        add(logo);
        add(navBarItems);
    }

    public JPanel logo(){
        JPanel myLogo = new JPanel();
        myLogo.setBackground(new Color(53,32,88));
        myLogo.setBounds(53, 81, 200, 50);

        JLabel logoName = new JLabel("Customify");
        logoName.setFont(new Font("Montserrat", Font.BOLD, 29));
        logoName.setForeground(new Color(164, 166, 179));

        myLogo.add(logoName);
        return myLogo;
    }
}
