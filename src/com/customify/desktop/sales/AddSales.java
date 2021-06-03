/*
 * Created by:Hagenimana Yassin
 * Date:24/05/2021
 * this is form for adding a new sale made.
 */
package com.customify.desktop.sales;
import com.customify.desktop.layout.Layout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class AddSales extends JPanel{
   public Container addingSales() {
      Container container = new Container();

      JPanel content=new JPanel();
      content.setBounds(60, 97, 800, 330);
      content.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
      setBounds(20, 190, 1014, 597);


      JLabel headLabel = new JLabel("Sale a Product");
      headLabel.setFont(new Font("Tahoma",  Font.PLAIN, 14));
      headLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
      headLabel.setBounds(60, 42, 325, 50);
      headLabel.setForeground(new Color(53, 32, 88));
      container.add(headLabel);


      JLabel lbl_customer_id = new JLabel("Customer ID");
      lbl_customer_id.setFont(new Font("Tahoma", Font.PLAIN, 16));
      lbl_customer_id.setBounds(100, 110, 150, 40);
      lbl_customer_id.setForeground(new Color(53, 32, 88));
      container.add(lbl_customer_id);



      JLabel lbl_quantity = new JLabel("Quantity");
      lbl_quantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
      lbl_quantity.setBounds(100, 220, 110, 29);
      lbl_quantity.setForeground(new Color(53, 32, 88));
      container.add(lbl_quantity);


      JTextField customer_id = new JTextField();
      customer_id.setFont(new Font("Tahoma", Font.PLAIN, 15));
      customer_id.setBounds(100, 151, 280, 30);
      customer_id.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
      container.add(customer_id);


      JTextField quantity = new JTextField();
      quantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
      quantity.setBounds(100, 255, 280, 30);
      quantity.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
      container.add(quantity);



      JLabel lbl_product_id = new JLabel("Product ID");
      lbl_product_id.setFont(new Font("Tahoma", Font.PLAIN, 16));
      lbl_product_id.setBounds(450, 120, 99, 29);
      lbl_product_id.setForeground(new Color(53, 32, 88));
      container.add(lbl_product_id);

      JLabel lbl_price = new JLabel("Total Price");
      lbl_price.setFont(new Font("Tahoma", Font.PLAIN, 16));
      lbl_price.setBounds(450, 220, 110, 29);
      lbl_price.setForeground(new Color(53, 32, 88));
      container.add(lbl_price);


      JTextField product_id = new JTextField();
      product_id.setFont(new Font("Tahoma", Font.PLAIN, 15));
      product_id.setBounds(450, 151, 280, 30);
      product_id.setColumns(10);
      product_id.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
      container.add(product_id);


      JTextField price = new JTextField();
      price.setFont(new Font("Tahoma", Font.PLAIN, 15));
      price.setBounds(450, 255, 280, 30);
      price.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
      container.add(price);
      price.setColumns(10);


      JButton addButton = new JButton("Add");
      addButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
      addButton.setBounds(450, 350, 120, 35);
      addButton.setBackground(new Color(53, 32, 88));
      addButton.setForeground(Color.white);
      addButton.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));


      container.add(addButton);


      JButton cancel = new JButton("Cancel");
      cancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
      cancel.setBounds(600, 350, 120, 35);
      cancel.setForeground(new Color(53, 32, 88));
      cancel.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));

      cancel.setBackground(Color.white);
      container.add(cancel);
      container.add(content);


      cancel.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            customer_id.setText("");
            quantity.setText("");
            product_id.setText("");
            price.setText("");
         }
      });
      return container;
   }



   public static void main(String[] args) throws IOException {
<<<<<<< HEAD:src/com/customify/desktop/sales/AddSales.java
     new Layout(new AddSales().addingSales(),"Add Sales");
=======
//     new Layout(new AddSales().addingSales());
>>>>>>> d0f46e65057169825f29d25b794dee44c6ae4eda:src/com/customify/desktop/layout/AddSales.java
   }
}
