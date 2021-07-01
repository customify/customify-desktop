package com.customify.desktop.customer;

import com.customify.cli.Keys;
import com.customify.cli.services.BusinessService;
import com.customify.desktop.services.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class SearchCustomer extends JPanel {
    public List<String> searchResult;
    public Boolean searched;
    private Socket socket;
    public SearchCustomer(Socket socket){
        this.socket=socket;
        JPanel search = new JPanel();
        search.setBackground(Color.white);
//        panel2.setPreferredSize(new Dimension(300, 100));
        JTextField searchField = new JTextField("Search");
        searchField.setPreferredSize(new Dimension(300, 25));
        searchField.setFont(new Font("Montserrat", Font.PLAIN, 12));
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(50,100,95,30);
        searchButton.setPreferredSize(new Dimension(80, 24));
        searchButton.setBackground(new Color(53,32,88));
        searchButton.setForeground(Color.white);
        searchButton.setFont(new Font("Montserrat", Font.BOLD, 12));
        searchButton.setBorderPainted(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    search(searchField.getText());

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
//        searchButton.setPreferredSize(new Dimension(100, 20));
        search.add(searchField);
        search.add(searchButton);
        add(search);
        setBackground(Color.white);
    }

    public SearchCustomer() {

    }

    private void search(String searchTerm) throws IOException, ClassNotFoundException {
        CustomerService service = new CustomerService(this.socket);
        String json = "{ \"name\" : \""+searchTerm+"\", \"key\" : \""+ Keys.GET_CUSTOMER +"\" }";
        this.searchResult= service.get(json);
        this.searched = true;
    }
}
