/**
 @author GISA KAZE Fredson
 Date: 20/03/2021
 */

package com.customify.desktop.points;

import com.customify.cli.services.PointsService;
import com.customify.desktop.layout.Layout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class PointsServices extends JPanel {
    private List<String> response;
    private Socket socket;
    DefaultTableModel model;
    Container c = new Container();
    String column[] = {"Customer Id", "First Name", "Last Name", "Email", "Points", "Winning Date", "Customer Code"};

    public PointsServices(){}
    public void returnWinner(Socket socket) throws IOException {
        PointsService pointsService = new PointsService(socket);
        response =  pointsService.getWinnersUi(socket);
    }

    public void init(Socket socket, JFrame closableFrame) throws IOException {
//        call function to return winner in desktop table
        returnWinner(socket);
        Container main = new Container();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
//        main.setBackground(Color.white);
        setLayout(null);

        JPanel header = new JPanel();
        JLabel headline = new JLabel("Winners ");
        headline.setPreferredSize(new Dimension(700, 200));
        Border border=headline.getBorder();
        Border margin=new EmptyBorder(100,450,50,0);
        headline.setBorder(new CompoundBorder(border,margin));
        headline.setFont(new Font("Montserrat", Font.BOLD, 24));
        headline.setForeground(new Color(53, 32, 88));
//        header.setBackground(Color.white);


        JTable table = new JTable();
        table.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setRowHeight(40);

//        Aligning content at the center in table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) :new Color(253, 249, 249));
                return c;
            }
        };
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(Object.class, centerRenderer);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);


//        checking if there is any data in database
        if(response.size()>=0){
            ObjectMapper objectMapper = new ObjectMapper();
            for (int i = 0; i < response.size(); i++) {
                JsonNode node = objectMapper.readTree(response.get(i));
                model.addRow(new Object[]{node.get("customerId").asText(),node.get("firstName").asText(), node.get("lastName").asText(), node.get("email").asText(), node.get("noPoints").asDouble(), node.get("winingDate").asText(), node.get("code").asText()});
            }
        }
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(53, 32, 88));
        tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
        tableHeader.setForeground(Color.white);
        tableHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tableHeader.setPreferredSize(new Dimension(100, 32));

        header.add(headline);
        header.setBorder(BorderFactory.createEmptyBorder(70, -390, 3, 1));
        main.add(header);
        main.add(new JScrollPane(table));
        main.setBounds(10, -100, 1040, 650);


        //previous btn
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(Box.createGlue());
        JButton prevButton = new JButton("Previous");
        prevButton.setForeground(new Color(53, 32, 88));
        prevButton.setBackground(Color.gray);
        buttonPanel.add(prevButton);


        //next button
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        buttonPanel.add(Box.createVerticalStrut(50));
        buttonPanel.add(Box.createGlue());
        JButton nextButton = new JButton("Next");
        nextButton.setBackground(new Color(53, 32, 88));
        nextButton.setForeground(Color.white);
        buttonPanel.add(nextButton);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,-13,0));
        main.add(buttonPanel);

        add(main);
        c.add(main);
        System.out.println("Here..........");
        new Layout(main, "Winners", socket);
    }

//    main method for calling main layout
//    public PointsServices(Socket socket) throws IOException, ClassNotFoundException{
//        this.socket = socket;
//        new Layout(init(), "Points Services", socket);
//
//    }
}