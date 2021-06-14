package com.customify.desktop.components;

import com.customify.desktop.business.ReadBusiness;
import com.customify.desktop.customerFeedback.CustomerFeedbackForm;
import com.customify.desktop.employee.ReadEmployees;
import com.customify.desktop.employee.UpdateEmployee;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.plans.ui.PlanAssign;
import com.customify.desktop.plans.ui.PlanHome;
import com.customify.desktop.plans.ui.PlanRegister;
import com.customify.desktop.points.PointsServices;
import com.customify.desktop.product.ReadProduct;
import com.customify.desktop.sales.Sales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class Sidebar extends JPanel {
    public Overview overview = new Overview();
    public Layout layout;
    public FeatureRegister featureRegister;
//    public Socket socket;
    public String role;


    public Sidebar(){}


//    public Sidebar(Socket socket){
//        this.socket = socket;
//    }

    public Sidebar(String role,JFrame closableFrame, Socket socket) throws IOException {
        setBackground(new Color(53, 32, 88));
        setBounds(0, 0, 300, 1080);
        setLayout(null);

//        Socket socket = new Socket("localhost",3000);

        JPanel navBarItems = new JPanel();
        navBarItems.setLayout(new BoxLayout(navBarItems, BoxLayout.Y_AXIS));
        navBarItems.setBounds(0, 180, 250, 200);
        navBarItems.setBackground(new Color(53, 32, 88));

        JButton overView = new SideBarListItem("Overview.png", "Overview");
        JButton sales = new SideBarListItem("4. contacts.png", "Sales");
        JButton employees = new SideBarListItem("4. contacts.png", "Employees");
        JButton customers = new SideBarListItem("4. contacts.png", "Customers");
        JButton report = new SideBarListItem("4. contacts.png", "Report");
        JButton feedback = new SideBarListItem("4. contacts.png", "Feedback");
        JButton settings = new SideBarListItem("4. contacts.png", "Settings");
        JButton subscription = new SideBarListItem("4. contacts.png", "Subscription");
        JButton features = new SideBarListItem("4. contacts.png", "Features");
        JButton plans = new SideBarListItem("4. contacts.png", "Plans");
        JButton billing = new SideBarListItem("4. contacts.png", "Billing");
        JButton business = new SideBarListItem("4. contacts.png", "Businesses");
        JButton product = new SideBarListItem("4. contacts.png", "Products");
        JButton winners = new SideBarListItem("4. contacts.png", "Winners");


        System.out.println("Test........."+role);


        switch (role){
            case "SUPER_ADMIN":
                navBarItems.add(overView);
                navBarItems.add(business);
                navBarItems.add(features);
                navBarItems.add(plans);
                navBarItems.add(report);
                navBarItems.add(sales);
                navBarItems.add(subscription);
                navBarItems.add(settings);
                navBarItems.add(employees);
                break;
            case "BUSINESS_ADMIN":
                navBarItems.add(overView);
                navBarItems.add(product);
                navBarItems.add(feedback);
                navBarItems.add(sales);
                navBarItems.add(employees);
                navBarItems.add(customers);
                navBarItems.add(report);
                navBarItems.add(winners);

                break;
            case "EMPLOYEE":
                navBarItems.add(overView);
                navBarItems.add(customers);
                navBarItems.add(sales);
                navBarItems.add(settings);
                navBarItems.add(plans);
                navBarItems.add(features);
                navBarItems.add(employees);
                navBarItems.add(business);
                break;
        }



        JPanel line = new JPanel();
        line.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(164, 166, 179)));
        line.setSize(100, 100);
        line.setBackground(new Color(53, 32, 88));


        //open features event

        features.addActionListener(e->{
            closableFrame.dispose();
            try {
                featureRegister = new FeatureRegister();
                featureRegister.init(socket);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        plans.addActionListener(e->{
            closableFrame.dispose();
            try {
                PlanHome planHome = new PlanHome();
                planHome.init(socket);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        employees.addActionListener(e -> {
            closableFrame.dispose();
            try {
                new ReadEmployees(socket,closableFrame);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        business.addActionListener(e->{
            closableFrame.dispose();
            try {
                ReadBusiness readBusiness = new ReadBusiness(socket,closableFrame);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        //open business event
        ActionListener triggerBusiness = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closableFrame.dispose();
                try {
                    new Layout(new ReadBusiness(socket,closableFrame), "Read All businesses", socket);

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        //open business feedback
        ActionListener triggerFeedback = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closableFrame.dispose();
                try {
                    CustomerFeedbackForm customerFeedbackForm = new CustomerFeedbackForm(socket,closableFrame);

                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        feedback.addActionListener(triggerFeedback);

        //open sales feedback
        ActionListener triggerSales = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closableFrame.dispose();
                try {
                    Sales sales = new Sales();
                    sales.DisplaySales(socket, closableFrame);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        sales.addActionListener(triggerSales);


        //open sales feedback
        ActionListener triggerProducts = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closableFrame.dispose();
                try {
                    ReadProduct products = new ReadProduct(socket, closableFrame);



                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        product.addActionListener(triggerProducts);


        //open sales feedback
        ActionListener triggerWinners = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closableFrame.dispose();
                try {
                    PointsServices pointsServices = new PointsServices();
                    pointsServices.init(socket, closableFrame);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        winners.addActionListener(triggerWinners);


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
