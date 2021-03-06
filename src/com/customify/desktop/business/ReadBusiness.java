package com.customify.desktop.business;

import com.customify.cli.Keys;
import com.customify.cli.services.BusinessService;
import com.customify.desktop.layout.Layout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ReadBusiness extends JPanel {
    private Socket socket;
    public List<String> searchResult;
    public Boolean searched=false;
    private List<String> bussData;
    JTable table;
    DefaultTableModel model;
    JButton searchButton;
    JButton resetButton;
    JTextField searchField;
    String column[]={"Business ID","Name","Location", "Address", "Phone Number", "Date created", "Action"};
    public ReadBusiness(Socket socket, JFrame closableFrame) throws IOException, ClassNotFoundException {
        this.socket=socket;
        this.getData();
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        setLayout(null);

        JPanel header = new JPanel();
        JLabel headline = new JLabel("All businesses ");
        headline.setPreferredSize(new Dimension(300, 100));
        headline.setFont(new Font("Montserrat", Font.BOLD, 25));
        headline.setForeground(new Color(53,32,88));
        header.setBackground(Color.white);

        JPanel newButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        newButton.setPreferredSize(new Dimension(100, 30));
        JButton bLabel = new JButton("New");
        bLabel.setFont(new Font("Montserrat", Font.PLAIN, 13));
        bLabel.setForeground(Color.white);
        bLabel.setBackground(new Color(53,32,88));
        bLabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    new Layout(new NewBusiness(socket),"Create a new business ", socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        createTable();

        //search/////////
        JPanel search = new JPanel();
        search.setLayout(new FlowLayout(FlowLayout.RIGHT));
        search.setBackground(Color.white);
        search.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
        searchField = new JTextField("Search");
        searchField.setPreferredSize(new Dimension(300, 25));
        searchField.setFont(new Font("Montserrat", Font.PLAIN, 12));
        resetButton = new JButton("Reset");
        resetButton.setActionCommand("RESET");
        resetButton.setBounds(50,100,95,30);
        resetButton.setPreferredSize(new Dimension(80, 24));
        resetButton.setBackground(new Color(53,32,88));
        resetButton.setForeground(Color.white);
        resetButton.setFont(new Font("Montserrat", Font.BOLD, 12));
        resetButton.setBorderPainted(false);
        resetButton.setVisible(false);
        resetButton.addActionListener(new SearchActionListener());
        searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.setActionCommand("SEARCH");
        searchButton.setBounds(50,100,95,30);
        searchButton.setPreferredSize(new Dimension(80, 24));
        searchButton.setBackground(new Color(53,32,88));
        searchButton.setForeground(Color.white);
        searchButton.setFont(new Font("Montserrat", Font.BOLD, 12));
        searchButton.setBorderPainted(false);
        searchButton.addActionListener(new SearchActionListener());
        search.add(searchField);
        search.add(searchButton);
        search.add(resetButton);
        add(search);
        setBackground(Color.white);

        //search end

        header.add(headline);
        newButton.add(bLabel);
        header.add(newButton);
        header.setBorder(BorderFactory.createEmptyBorder(1,-390,3,1));

        main.add(header);
        main.add(search);
        main.add(new JScrollPane(table));

        main.setBounds(30, 50, 800, 530);

        add(main);
        setBackground(Color.WHITE);
    }

    public ReadBusiness() {

    }

    private void createTable() throws JsonProcessingException {
        table = new JTable();
        table.setBorder(BorderFactory.createEmptyBorder(15,10,0,0));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(30);
        table.setBounds(0,0,200,200);
//        table.setSize(new Dimension(500,500));
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
        if(this.bussData != null){
            ObjectMapper objectMapper = new ObjectMapper();

            for (int i = 1; i < this.bussData.size(); i++) {
                JsonNode bs = objectMapper.readTree(this.bussData.get(i));
                model.addRow(new Object[]{bs.get("id"), bs.get("name").textValue(), bs.get("location").textValue(), bs.get("address").textValue(), bs.get("phone_number").textValue(), bs.get("created_at").textValue(),"Action"});
            }
        }
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(53,32,88));
        tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
        tableHeader.setForeground(Color.white);
        tableHeader.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        tableHeader.setPreferredSize(new Dimension(100, 32));

    }

    private void getData() throws IOException, ClassNotFoundException {
        BusinessService service = new BusinessService(this.socket);
        String json = "{ \"key\" : \""+ Keys.GET_ALL_BUSINESSES +"\" }";
        service.getBusinesses(json);
    }

//    private void search(String searchTerm) throws IOException, ClassNotFoundException {
//        BusinessService service = new BusinessService(this.socket);
//        String json = "{ \"name\" : \""+searchTerm+"\", \"key\" : \""+ Keys.GET_BUSINESSES_BY_NAME +"\" }";
//        this.searchResult= service.getById(json);
//        this.searched = true;
//    }
//        this.bussData= service.getAllBusinesses(json);
//    }

    private void search(String searchTerm) throws IOException, ClassNotFoundException {
        BusinessService service = new BusinessService(this.socket);
        String json = "{ \"name\" : \""+searchTerm+"\", \"key\" : \""+ Keys.GET_BUSINESSES_BY_NAME +"\" }";
        this.searchResult= service.searchByName(json);
        this.searched = true;
    }

    class SearchActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="SEARCH"){
                try {
                    if(!searched){
                        resetButton.setVisible(true);
                    }
//                    search(searchField.getText());
                    search(searchField.getText());
                    model = new DefaultTableModel();
                    model.setColumnIdentifiers(column);
                    table.setModel(model);
                    if(searchResult != null){
                        ObjectMapper objectMapper = new ObjectMapper();

                        for (int i = 1; i < searchResult.size(); i++) {
                            JsonNode bs = objectMapper.readTree(searchResult.get(i));
                            model.addRow(new Object[]{bs.get("id"), bs.get("name").textValue(), bs.get("location").textValue(), bs.get("address").textValue(), bs.get("phone_number").textValue(), bs.get("created_at").textValue(),"Action"});
                        }
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }

            else if (e.getActionCommand()=="RESET"){
                searched=false;
//                    getData();
                model = new DefaultTableModel();
                model.setColumnIdentifiers(column);
                table.setModel(model);
                if(bussData != null){
                    ObjectMapper objectMapper = new ObjectMapper();

                    for (int i = 1; i < bussData.size(); i++) {
                        JsonNode bs = null;
                        try {
                            bs = objectMapper.readTree(bussData.get(i));
                        } catch (JsonProcessingException jsonProcessingException) {
                            jsonProcessingException.printStackTrace();
                        }
                        model.addRow(new Object[]{bs.get("id"), bs.get("name").textValue(), bs.get("location").textValue(), bs.get("address").textValue(), bs.get("phone_number").textValue(), bs.get("created_at").textValue(),"Action"});
                    }
                }

                resetButton.setVisible(false);
            }
            else{
                System.out.println("Action command not defined");
            }
        }
    }
}

