package com.customify.desktop.services.points;

import com.customify.desktop.layout.Layout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.IOException;

public class PointsServices extends JPanel {
    Container c = new Container();
    String data[][] = {
            {"CUST001", "Gisa Kaze", "Fredson", "fredson.coder@gmail.com", "14", "2021-05-21", "CZ001"},
            {"CUST001", "Gisa Kaze", "Fredson", "fredson.coder@gmail.com", "14", "2021-05-21", "CZ001"},
            {"CUST001", "Gisa Kaze", "Fredson", "fredson.coder@gmail.com", "14", "2021-05-21", "CZ001"},
            {"CUST001", "Gisa Kaze", "Fredson", "fredson.coder@gmail.com", "14", "2021-05-21", "CZ001"},
            {"CUST001", "Gisa Kaze", "Fredson", "fredson.coder@gmail.com", "14", "2021-05-21", "CZ001"},
            {"CUST001", "Gisa Kaze", "Fredson", "fredson.coder@gmail.com", "14", "2021-05-21", "CZ001"},
            {"CUST001", "Gisa Kaze", "Fredson", "fredson.coder@gmail.com", "14", "2021-05-21", "CZ001"}
    };
    String column[] = {"Customer Id", "First name", "Last name", "Email", "Points", "Winning date", "Customer code"};

    public Container init() {
        JPanel main = new JPanel();
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
//        setBackground(Color.WHITE);
        c.add(main);
        return c;
    }
    public static void main(String args[]) throws IOException {
        new Layout(new PointsServices().init(), "Points Services");
    }
}