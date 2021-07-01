package com.customify.desktop.features;

import com.customify.desktop.layout.Layout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;


public class Features  {
    public Layout layout;
    public Socket socket;
    public  Features() {}
        Container container=new Container();
        public void init(Socket socket) throws IOException {
            JLabel heading = new JLabel("Billing Features");
            heading.setFont(new Font("Tahoma", Font.BOLD, 20));
            heading.setBounds(30, 42, 325, 50);
            heading.setForeground(new Color(53, 32, 88));
            container.add(heading);


            JButton newButton = new JButton("+ New");
            newButton.setBounds(940,50,90, 35);
            newButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
            newButton.setForeground(new Color(53, 32, 88));
            newButton.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
            newButton.setBackground(Color.white);
            container.add(newButton);



            JPanel main = new JPanel();
            main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
            main.setBackground(Color.white);
            container.setLayout(null);
            SearchFeatureForm myFrame = new SearchFeatureForm();
            myFrame.setSize(1000,300);
            myFrame.setVisible(true);
            myFrame.setLocation(0,100);
            container.add(myFrame);
            layout = new Layout(container, "Billing Features", socket);

    }

}
