/*
  By Makuza Mugabo Verite
  On 8 May 2020
 */

package com.customify.desktop.sales;

import com.customify.desktop.layout.Layout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.IOException;

public class Sales {

     Container c = new Container();


    public Sales() throws IOException {
        new Layout(this.listSales());
    }

    public Container listSales()  {
        JPanel panel = new JPanel();
        panel.setBounds(4040,80,200,200);
        panel.setBackground(Color.red);

        c.add(tableListing());
        return c;
    }


    public JTable tableListing(){

        Object[][] rows = {{"Verite","Hello","Makuza"},{"Verite","Hello","Makuza"},{"Verite","Hello","Makuza"},{"Verite","Hello","Makuza"}};
        String[] columns = {"First Name", "Greeting", "Last Name"};

        TableModel model = new DefaultTableModel(rows,columns);
        JTable jTable = new JTable(model);


        return jTable;
    }

}
