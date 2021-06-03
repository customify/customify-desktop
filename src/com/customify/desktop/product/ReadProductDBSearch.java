package com.customify.desktop.product;

import com.customify.cli.Keys;
import com.customify.cli.services.ProductService;
import com.customify.desktop.business.Search;
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
import java.net.Socket;
import java.util.List;
import java.io.IOException;

public class ReadProductDBSearch extends JPanel {

    private Socket socket;
    private List<String> searchResult;
    public Boolean searched = false;
    private List<String> productData;

    JTable table;
    DefaultTableModel model;
    JButton searchButton;
    JButton resetButton;
    JTextField searchField;


    String column[]={"Product Code","Business Id","Name", "Price", "Quantity", "Description", "Bonded points","Registered_by","Created At"};

    public ReadProductDBSearch(Socket socket) throws Exception {
        this.socket = socket;
        this.getData();

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        setLayout(null);

        JPanel header = new JPanel();
        JLabel headline = new JLabel("Products in the Store ");
        headline.setPreferredSize(new Dimension(300, 100));
        headline.setFont(new Font("Montserrat", Font.BOLD, 25));
        headline.setForeground(new Color(53,32,88));
        header.setBackground(Color.white);

        JPanel newButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        newButton.setPreferredSize(new Dimension(100, 30));
//        newButton.setBorder(BorderFactory.createEmptyBorder(0,500,0,0));
        JLabel bLabel = new JLabel("New");
        bLabel.setPreferredSize(new Dimension(100, 30));
        bLabel.setFont(new Font("Montserrat", Font.PLAIN, 13));
        bLabel.setForeground(new Color(53,32,88));
        bLabel.setBackground(Color.white);
        bLabel.setBorder(new CompoundBorder(bLabel.getBorder(), new EmptyBorder(10,40,20,10)));

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

//        JTable table = new JTable();
//        table.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
//        table.setShowGrid(false);
//        table.setIntercellSpacing(new Dimension(0, 0));
//        table.setRowHeight(30);
//        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
//            {
//                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                c.setBackground(row % 2 == 0 ? new Color(253, 249, 249) : new Color(240, 240, 240));
//                return c;
//            }
//        });
//
//        DefaultTableModel model = new DefaultTableModel();
//        model.setColumnIdentifiers(column);
//        table.setModel(model);
//        if(this.productData != null) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            for (int i = 0; i < productData.size(); i++) {
//                JsonNode productNode = objectMapper.readTree(this.productData.get(i));
//                model.addRow(new Object[]{productNode.get("productCode"), productNode.get("business_id"), productNode.get("name"), productNode.get("price"), productNode.get("quantity") , productNode.get("description") , productNode.get("bondedPoints") , productNode.get("registered_by") , productNode.get("createdAt")});
//            }
//        }
//        JTableHeader tableHeader = table.getTableHeader();
//        tableHeader.setBackground(new Color(53,32,88));
//        tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
//        tableHeader.setForeground(Color.white);
//        tableHeader.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
//        tableHeader.setPreferredSize(new Dimension(100, 32));
//
//        header.add(headline);
//        newButton.add(bLabel);
//        header.add(newButton);
//        header.setBorder(BorderFactory.createEmptyBorder(1,-390,3,1));
//
//        JPanel panel2 = new Search();
//        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));
//
//        main.add(header);
//        main.add(panel2);
//        main.add(new JScrollPane(table));
//
//        main.setBounds(30, 50, 800, 600);
//
//        add(main);
//        setBackground(Color.WHITE);
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
        if(this.productData != null){
            ObjectMapper objectMapper = new ObjectMapper();

            for (int i = 1; i < this.productData.size(); i++) {
//                JsonNode bs = objectMapper.readTree(this.productData.get(i));
//                model.addRow(new Object[]{bs.get("id"), bs.get("name").textValue(), bs.get("location").textValue(), bs.get("address").textValue(), bs.get("phone_number").textValue(), bs.get("created_at").textValue(),"Action"});

                JsonNode productNode = objectMapper.readTree(this.productData.get(i));
                model.addRow(new Object[]{productNode.get("productCode"), productNode.get("business_id"), productNode.get("name"), productNode.get("price"), productNode.get("quantity") , productNode.get("description") , productNode.get("bondedPoints") , productNode.get("registered_by") , productNode.get("createdAt")});
            }
        }
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(53,32,88));
        tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
        tableHeader.setForeground(Color.white);
        tableHeader.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        tableHeader.setPreferredSize(new Dimension(100, 32));

    }

    private void getData() throws Exception {
        ProductService service = new ProductService(this.socket);
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode productNode = objectMapper.readTree(service.getAllProducts().get(0));
//        System.out.println("Product node "+productNode);
//        System.out.println("Testing paths"+productNode.at("/products/productCode"));
        this.productData = service.getAllProducts();
        //
        System.out.println("Results"+productData);
    }

    private void search(String searchTerm) throws IOException, ClassNotFoundException {
        ProductService service = new ProductService(this.socket);
        String json = "{ \"name\" : \""+searchTerm+"\", \"key\" : \""+ Keys.GET_PRODUCTS_BY_NAME +"\" }";
        this.searchResult= service.searchByName(json);
        this.searched = true;
    }

    class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand()=="SEARCH"){
                try {
                    if(!searched){
                        resetButton.setVisible(true);
                    }
                    search(searchField.getText());
                    model = new DefaultTableModel();
                    model.setColumnIdentifiers(column);
                    table.setModel(model);
                    if(searchResult != null){
                        ObjectMapper objectMapper = new ObjectMapper();

                        for (int i = 1; i < searchResult.size(); i++) {
//                            JsonNode bs = objectMapper.readTree(searchResult.get(i));
//                            model.addRow(new Object[]{bs.get("id"), bs.get("name").textValue(), bs.get("location").textValue(), bs.get("address").textValue(), bs.get("phone_number").textValue(), bs.get("created_at").textValue(),"Action"});
                            JsonNode productNode = objectMapper.readTree(searchResult.get(i));
                            model.addRow(new Object[]{productNode.get("productCode"), productNode.get("business_id"), productNode.get("name"), productNode.get("price"), productNode.get("quantity") , productNode.get("description") , productNode.get("bondedPoints") , productNode.get("registered_by") , productNode.get("createdAt")});
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
                if(productData != null){
                    ObjectMapper objectMapper = new ObjectMapper();

                    for (int i = 1; i < productData.size(); i++) {
                        JsonNode productNode = null;
                        try {
                            productNode = objectMapper.readTree(productData.get(i));
                        } catch (JsonProcessingException jsonProcessingException) {
                            jsonProcessingException.printStackTrace();
                        }
                        model.addRow(new Object[]{productNode.get("productCode"), productNode.get("business_id"), productNode.get("name"), productNode.get("price"), productNode.get("quantity") , productNode.get("description") , productNode.get("bondedPoints") , productNode.get("registered_by") , productNode.get("createdAt")});
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

