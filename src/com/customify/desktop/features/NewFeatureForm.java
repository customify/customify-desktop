package com.customify.desktop.features;

import com.customify.server.models.billing.Feature;
import com.customify.server.services.billing.FeatureDao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class NewFeatureForm extends JFrame implements ActionListener{
    private SearchFeatureForm parent;
    private Feature client;
    private int index;
    private JTextField txtId, txtName, txtDescription;
    private JButton btnUpdate, btnReset;


    public NewFeatureForm(SearchFeatureForm parent, int index){


        super("Add a Feature");
        this.parent = parent;
        this.index = index;

        txtId = new JTextField(15);
        txtId.setEditable(false);
        txtName = new JTextField(15);
        txtDescription = new JTextField(15);
        btnUpdate = new JButton("Save");
        btnReset = new JButton("Reset");

        JPanel content = new JPanel();



        content.setLayout(new GridLayout(7,2));
//        content.add(new JLabel("ID:"));     content.add(txtId);
        content.add(new JLabel("Name:"));     content.add(txtName);
        content.add(new JLabel("Description:"));     content.add(txtDescription);
        content.add(btnUpdate);

        btnUpdate.addActionListener(this);
        btnReset.addActionListener(this);

        initForm();

        this.setContentPane(content);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initForm(){
            txtName.setText("");
            txtDescription.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(btnReset)){
            initForm();
            return;
        }
        if(btnClicked.equals(btnUpdate)){
            btnUpdateClick();
        }
    }

    private void btnUpdateClick(){
        client.setName(txtName.getText());
        client.setDescription(txtDescription.getText());

        FeatureDao clientDAO = new FeatureDao();
        clientDAO.saveFeature(client);
        parent.refreshResultAfterUpdate(index+1, client);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}