package com.customify.desktop.business;

import com.customify.desktop.components.FormControl;
import com.customify.desktop.layout.Layout;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class ReadBusiness extends Container {
    public Layout layout;
    public Socket socket;

    String data[][]={ {"101","Amit","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"101","Sachin","670000","670000","670000","670000","670000"}};
    String column[]={"Business ID","Name","Location", "Address", "Phone Number", "Date created", "Action"};
    public ReadBusiness(Socket socket,JFrame closableFrame) throws IOException {
        this.socket = socket;

        Container main = new Container();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        setLayout(null);

        JPanel header = new JPanel();
        JLabel headline = new JLabel("All businesses ");
        headline.setPreferredSize(new Dimension(300, 100));
        headline.setFont(new Font("Montserrat", Font.BOLD, 25));
        headline.setForeground(new Color(53,32,88));
//        header.setBackground(Color.white);

        JPanel newButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        newButton.setPreferredSize(new Dimension(100, 30));
//        newButton.setBorder(BorderFactory.createEmptyBorder(0,500,0,0));
        JButton bLabel = new JButton("New");
//        bLabel.setPreferredSize(new Dimension(100, 30));

        bLabel.setFont(new Font("Montserrat", Font.PLAIN, 13));
        bLabel.setBounds(400, 100,400,200);

//        bLabel.setForeground(new Color(53,32,88));
//        bLabel.setBackground(Color.white);
//        bLabel.setBorder(new CompoundBorder(bLabel.getBorder(), new EmptyBorder(10,40,20,10)));

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
        for (int i = 0; i < 7; i++) {
                model.addRow(new Object[]{data[i][0],data[i][1],data[i][2],data[i][3],data[i][4],data[i][5],data[i][6]});

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
        ActionListener triggerCreateBus = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    closableFrame.dispose();
                    new NewBusiness(socket);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        };
        bLabel.addActionListener(triggerCreateBus);
        layout = new Layout(main, "List business", socket);
    }

//    public static  void main(String args[]) throws IOException {
//        new ReadBusiness();
//    }

//    public void init() throws IOException {
//        new ReadBusiness();
//    }

}
