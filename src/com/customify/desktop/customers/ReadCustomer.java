package com.customify.desktop.customers;


import com.customify.desktop.business.Search;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ReadCustomer extends JPanel {
    String data[][]={ {"062187","Habumugisha","Anselme","anselme@gmail.com","ACTIVE"},
            {"062187","Habumugisha","Anselme","anselme@gmail.com","ACTIVE"},
            {"062187","Habumugisha","Anselme","anselme@gmail.com","ACTIVE"},
            {"062187","Habumugisha","Anselme","anselme@gmail.com","ACTIVE"},
            {"062187","Habumugisha","Anselme","anselme@gmail.com","ACTIVE"},
            {"062187","Habumugisha","Anselme","anselme@gmail.com","ACTIVE"},
            {"062187","Habumugisha","Anselme","anselme@gmail.com","ACTIVE"}};
    String column[]={"Customer ID","First_Name","Last_Name", "Email", "Status"};
    public ReadCustomer(){
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        setLayout(null);

        JPanel header = new JPanel();
        JLabel headline = new JLabel("ALL CUSTOMERS ");
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

        JTable table = new JTable();
        table.setBorder(BorderFactory.createEmptyBorder(10,10,0,0));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(30);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(253, 249, 249) : new Color(240, 240, 240));
                return c;
            }
        });

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        table.setModel(model);
        for (int i = 0; i < 5; i++) {
            model.addRow(new Object[]{data[i][0],data[i][1],data[i][2],data[i][3],data[i][4]});

        }
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(53,32,88));
        tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
        tableHeader.setForeground(Color.white);
        tableHeader.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        tableHeader.setPreferredSize(new Dimension(100, 32));

        header.add(headline);
        newButton.add(bLabel);
        header.add(newButton);
        header.setBorder(BorderFactory.createEmptyBorder(1,-390,3,1));

        JPanel panel2 = new Search();
        panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        main.add(header);
        main.add(panel2);
        main.add(new JScrollPane(table));

        main.setBounds(30, 50, 800, 600);

        add(main);
        setBackground(Color.WHITE);
    }


}
