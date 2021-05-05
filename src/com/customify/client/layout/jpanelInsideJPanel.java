/**
 * File: jpanelInsideJPanel.java
 * Tiltle: Adding JPanel Inside Another JPanel
 * Author: http://java-program-sample.blogspot.com/
 */
package com.customify.client.layout;


//Java Core Package
import javax.swing.*;
//Java Extension Package
import java.awt.*;

public class jpanelInsideJPanel extends JFrame {

    //Initializing JPanels
    private JPanel mainPanel, subPanel1, subPanel2, subPanel3, subPanel4;

    //Setting up GUI
    public jpanelInsideJPanel(){

        //Setting up the Title of the Window
        super("Adding JPanel Inside Another JPanel");

        //Set Size of the Window (WIDTH, HEIGHT)
        setSize(350,250);

        //Exit Property of the Window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Constructing Main JPanel with GridLayout of 1 row and 2 column
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));
        mainPanel.setLayout(new GridLayout(1,2));

        //Constructing JPanel 1 and 2 with GridLayout of 1 row and 1 column
        subPanel1 = new JPanel();
        subPanel1.setBorder(BorderFactory.createTitledBorder("Sub Panel 1"));
        subPanel1.setLayout(new GridLayout(1,1));
        subPanel2 = new JPanel();
        subPanel2.setBorder(BorderFactory.createTitledBorder("Sub Panel 2"));
        subPanel2.setLayout(new GridLayout(1,1));

        //Constructing JPanel 3 and 4
        subPanel3 = new JPanel();
        subPanel3.setBorder(BorderFactory.createTitledBorder("Sub Panel 3"));
        subPanel4 = new JPanel();
        subPanel4.setBorder(BorderFactory.createTitledBorder("Sub Panel 4"));

        //Adding JPanel 3 to JPanel 1 which means JPanel 3 is inside JPanel 1
        subPanel1.add(subPanel3);
        //Adding JPanel 4 to JPanel 2 which means JPanel 4 is inside JPanel 2
        subPanel2.add(subPanel4);

        //Adding JPanel 1 and 2 to main JPanel
        mainPanel.add(subPanel1);
        mainPanel.add(subPanel2);

        //Setting up the container ready for the components to be added.
        Container pane = getContentPane();
        setContentPane(pane);

        //Adding the main JPanel to the container
        pane.add(mainPanel);

        /**Set all the Components Visible.
         * If it is set to "false", the components in the container will not be visible.
         */
        setVisible(true);
    }

    //Main Method
    public static void main (String[] args) {
        jpanelInsideJPanel jp = new jpanelInsideJPanel();



    }
}


//Constructing Main JPanel with GridLayout of 1 row and 2 column
