package com.customify.desktop.features;

import com.customify.server.models.billing.Feature;
import com.customify.server.services.billing.FeatureDao;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditFeatureForm extends JFrame implements ActionListener{
    private SearchFeatureForm parent;
    private Feature client;
    private int index;
    private JTextField txtId, txtName, txtDescription;
    private JButton btnUpdate, btnReset;


    public EditFeatureForm(SearchFeatureForm parent, Feature client, int index){
        super("Edit a Feature");
        this.parent = parent;
        this.client = client;
        this.index = index;

        txtId = new JTextField(15);
        txtId.setEditable(false);
        txtName = new JTextField(15);
        txtDescription = new JTextField(15);
        btnUpdate = new JButton("Update");
        btnReset = new JButton("Reset");

        JPanel content = new JPanel();
        content.setLayout(new GridLayout(7,2));
        content.add(new JLabel("ID:"));     content.add(txtId);
        content.add(new JLabel("Name:"));     content.add(txtName);
        content.add(new JLabel("Description:"));     content.add(txtDescription);
        content.add(btnUpdate);     content.add(btnReset);

        btnUpdate.addActionListener(this);
        btnReset.addActionListener(this);

        initForm();

        this.setContentPane(content);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initForm(){
        if(client != null){
            txtId.setText(client.getId()+"");
            txtName.setText(client.getName());
            txtDescription.setText(client.getDescription());
        }
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
        clientDAO.editFeature(client);
        parent.refreshResultAfterUpdate(index, client);
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

}