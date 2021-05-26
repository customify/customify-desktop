
/* Created BY: Hagenimana Yassin
   Date: 20/05/2021
 */
package com.customify.desktop.sales;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class Sales extends JPanel {
 String data[][] = {
         {"101", "Amit", "670000", "670000", "670000"},
         {"102", "Jai", "670000", "670000", "670000",},
         {"102", "Jai", "670000", "670000", "670000"},
         {"102", "Jai", "670000", "670000", "670000"},
         {"102", "Jai", "670000", "670000", "670000"},
         {"102", "Jai", "670000", "670000", "670000"},
         {"101", "Sachin", "670000", "670000", "670000"},
         {"101", "Sachin", "670000", "670000", "670000"},
         {"101", "Sachin", "670000", "670000", "670000"},
         {"101", "Sachin", "670000", "670000", "670000"},
         {"101", "Sachin", "670000", "670000", "670000"},
         {"101", "Sachin", "670000", "670000", "670000"},
 };
 String column[] = {"Customer ID", "Quantity", "Total Price", "Employee ID", "Product ID"};

 public Sales() {
  JPanel main = new JPanel();
  main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
  main.setBackground(Color.white);
  setLayout(null);

  JPanel header = new JPanel();
  JLabel headline = new JLabel("Sale Product ");
  headline.setPreferredSize(new Dimension(700, 100));
  Border border=headline.getBorder();
  Border margin=new EmptyBorder(50,100,0,0);
  headline.setBorder(new CompoundBorder(border,margin));
  headline.setFont(new Font("Montserrat", Font.BOLD, 24));
  headline.setForeground(new Color(53, 32, 88));
  header.setBackground(Color.white);


  JButton newButton = new JButton();
  newButton.setPreferredSize(new Dimension(100, 30));
  JLabel bLabel = new JLabel("Add New");
  bLabel.setPreferredSize(new Dimension(100, 30));
  bLabel.setFont(new Font("Montserrat", Font.PLAIN, 14));
  bLabel.setForeground(new Color(5, 3, 8));
  bLabel.setBackground(Color.red);
  bLabel.setBorder(new CompoundBorder(bLabel.getBorder(), new EmptyBorder(20, 2, 20, 0)));
 newButton.setBounds(50,0,90, 50);



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

  DefaultTableModel model = new DefaultTableModel();
  model.setColumnIdentifiers(column);
  table.setModel(model);
  for (int i=0;i<data.length;i++) {
   model.addRow(data[i]);
  }
  JTableHeader tableHeader = table.getTableHeader();
  tableHeader.setBackground(new Color(53, 32, 88));
  tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
  tableHeader.setForeground(Color.white);
  tableHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
  tableHeader.setPreferredSize(new Dimension(100, 32));

  header.add(headline);
  newButton.add(bLabel);
  header.add(newButton);
  header.setBorder(BorderFactory.createEmptyBorder(70, -390, 3, 1));
  main.add(header);
  main.add(new JScrollPane(table));
  main.setBounds(10, -100, 1010, 600);


  //previous button
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
    setBackground(Color.WHITE);
 }
}