
/* Created BY: Hagenimana Yassin
   Date: 20/05/2021
 */
package com.customify.desktop.sales;
import com.customify.cli.services.PointsService;
import com.customify.cli.services.SalesService;
import com.customify.desktop.layout.Layout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Sales extends JPanel {
  private List<String> response;
  private static Socket socket;

  public Sales(){}
  public void returnSales(){
    SalesService salesService = new SalesService(socket);
    response = salesService.getSalesUi();
  }
 public  Container DisplaySales() throws JsonProcessingException {
    returnSales();
   Container container=new Container();

//   String[][] data = {
//       {"101", "Amit", "670000", "670000", "670000"},
//       {"102", "Jai", "670000", "670000", "670000",},
//       {"102", "Jai", "670000", "670000", "670000"},
//       {"102", "Jai", "670000", "670000", "670000"},
//       {"102", "Jai", "670000", "670000", "670000",},
//       {"102", "Jai", "670000", "670000", "670000"},
//       {"102", "Jai", "670000", "670000", "670000"},
//       {"102", "Jai", "670000", "670000", "670000",},
//       {"102", "Jai", "670000", "670000", "670000"},
//       {"102", "Jai", "670000", "670000", "670000"},
//   };
   String[] column = {"Customer ID", "Quantity", "Total Price", "Employee ID", "Product ID"};


   JLabel heading = new JLabel("Sale Products");
   heading.setFont(new Font("Tahoma", Font.BOLD, 20));
   heading.setBounds(30, 42, 325, 50);
   heading.setForeground(new Color(53, 32, 88));
   container.add(heading);


   JButton newButton = new JButton("Add New");
   newButton.setBounds(940,50,90, 35);
   newButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
   newButton.setForeground(new Color(53, 32, 88));
   newButton.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
   newButton.setBackground(Color.white);
   container.add(newButton);



   JPanel main = new JPanel();
   main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
   main.setBackground(Color.white);
   setLayout(null);

   JTable table = new JTable();
   table.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
   table.setShowGrid(false);
   table.setIntercellSpacing(new Dimension(0, 1));
   table.setRowHeight(40);

   table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
     @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
       c.setBackground(row % 2 == 0 ? new Color(253, 249, 249) : new Color(240, 240, 240));
       return c;
     }
   });

  DefaultTableModel model = new DefaultTableModel(0,0){
    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };
  model.setColumnIdentifiers(column);
  table.setModel(model);
//   for (String[] datum : data) {
//     model.addRow(datum);
//   }
   if(response.size()>=0){
     ObjectMapper objectMapper = new ObjectMapper();
     for (int i = 0; i < response.size(); i++) {
       JsonNode jsonNode = objectMapper.readTree(response.get(i));
       model.addRow(new Object[]{jsonNode.get("saleId"),jsonNode.get("customerID").textValue(),jsonNode.get("quantity").textValue(),jsonNode.get("totalPrice").textValue(),jsonNode.get("employeeID").textValue(),jsonNode.get("productID").textValue()});
     }
   }

  JTableHeader tableHeader = table.getTableHeader();
  tableHeader.setBackground(new Color(53, 32, 88));
  tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
  tableHeader.setForeground(Color.white);
  tableHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
  tableHeader.setPreferredSize(new Dimension(100, 32));
   main.add(new JScrollPane(table));
   main.setBounds(30, 100, 1000, 400);
   container.add(main);


   JButton prevButton = new JButton("Previous");
   prevButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
   prevButton.setBounds(830, 530, 90, 35);
   prevButton.setBackground(new Color(53, 32, 88));
   prevButton.setForeground(Color.white);
   prevButton.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
   container.add(prevButton);



   JButton nextButton = new JButton("Next");
   nextButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
   nextButton.setBounds(940, 530, 90, 35);
   nextButton.setForeground(new Color(53, 32, 88));
   nextButton.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
   nextButton.setBackground(Color.white);
   container.add(nextButton);

   return container;
 }


  public static void main(String[] args) throws  IOException{
    new Layout(new Sales().DisplaySales(),"Sales");
  }
}