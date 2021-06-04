package com.customify.desktop.product;

import com.customify.cli.data_format.products.ProductFormat;
import com.customify.cli.services.ProductService;
import com.customify.cli.Keys;
import com.customify.desktop.components.FormControl;
import com.customify.desktop.utils.interfaces.IInputChangedEventListener;




import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.net.Socket;

public class NewProduct extends JPanel {
     private Socket socket;

     ProductFormat format = new ProductFormat();

     public NewProduct(Socket socket){
        this.socket=socket;

       JPanel header = new JPanel();
       JPanel main = new JPanel();
       JLabel headline = new JLabel("ADD PRODUCT ");
       headline.setFont(new Font("Montserrat", Font.BOLD, 20));
       headline.setForeground(new Color(53,32,88));

       header.setBackground(Color.white);
       header.setBounds(100,90,300,30);
       header.add(headline);

       main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
       main.setBackground(Color.white);
       setLayout(null);
       main.setBorder(BorderFactory.createCompoundBorder(
               new LineBorder(Color.black, 1, true),
               BorderFactory.createEmptyBorder(8, 15, 8, 15))
       );
       JPanel main2=new JPanel();
       main2.setLayout(new GridLayout(4,2));
       main2.setBackground(Color.white);


       JPanel productName = new FormControl("Product name");
       JPanel productDescription = new FormControl("Product description");
       JPanel quantity = new FormControl("Quantity");
       JPanel points = new FormControl("Points");
       JPanel price = new FormControl("Price");

       JPanel buttonGroup = new JPanel();
       buttonGroup.setBackground(Color.white);

       JButton cancel = new JButton("Cancel");
       cancel.setBounds(400,400,100,20);
       cancel.setBackground(Color.white);
       cancel.setBorder(BorderFactory.createCompoundBorder(
               cancel.getBorder(),
               BorderFactory.createEmptyBorder(7, 90, 2, 90)));
       cancel.setFont(new Font("Montserrat", Font.PLAIN, 18));


       JButton btn = new JButton("ADD");
       btn.setBounds(1020,400,100,40);
       btn.setBackground(new Color(53,32,88));
       btn.setForeground(Color.white);
       btn.setBorder(BorderFactory.createCompoundBorder(
               btn.getBorder(),
               BorderFactory.createEmptyBorder(7, 90, 2, 90)));
       btn.setFont(new Font("Montserrat", Font.PLAIN, 18));

       buttonGroup.add(btn);
       buttonGroup.add(cancel);

       main2.add(productName);
       main2.add(quantity);
       main2.add(productDescription);
       main2.add(points);
       main2.add(price);
       main.add(main2);
       main.add(buttonGroup);

       main.setBounds(150, 120, 700, 500);
       main2.setBounds(150, 100, 600, 600);

       add(header);
       add(main);
       setBackground(Color.white);
  }

     public void createNewProduct() throws Exception {
          ProductService service = new ProductService(this.socket);
          this.format.setKey(Keys.CREATE_PRODUCT);
          service.addNewProduct(this.format);
     }

     public JPanel createNewInput(String placeholderTextParam){
          JPanel textFieldContainer = new JPanel();
          textFieldContainer.setBackground(Color.white);
          JLabel placeholderText = new JLabel(placeholderTextParam);
          placeholderText.setFont(new Font("Montserrat", Font.PLAIN, 18));
          placeholderText.setBackground(Color.green);
          placeholderText.setPreferredSize(new Dimension(200, 30));

          JTextField textField = new JTextField("", 20);
          textField.setBorder(BorderFactory.createCompoundBorder(
                  new LineBorder(Color.black, 1, true),
                  BorderFactory.createEmptyBorder(8, 15, 8, 15))
          );
          textField.setFont(new Font("Montserrat", Font.PLAIN, 18));

          textField.getDocument().addDocumentListener((IInputChangedEventListener) e -> {
               switch (placeholderTextParam) {
                    case "product name": format.setName(textField.getText());
                    case "product description" : format.setDescription(textField.getText());
                    case "Quantity" : format.setQuantity(Integer.parseInt(textField.getText()));
                    case "Points" : format.setBondedPoints(Integer.parseInt(textField.getText()));
                    case "Price" : format.setPrice(Integer.parseInt(textField.getText()));
              }
          });

          textFieldContainer.add(placeholderText);
          textFieldContainer.add(textField);

          return textFieldContainer;
     }
}