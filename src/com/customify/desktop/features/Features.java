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
