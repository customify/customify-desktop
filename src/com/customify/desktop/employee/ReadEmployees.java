package com.customify.desktop.employee;

import com.customify.cli.utils.authorization.structure.EmployeeUser;
import com.customify.desktop.business.NewBusiness;
import com.customify.desktop.business.Search;
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

public class ReadEmployees extends Container {
    public Layout layout;
    public Socket socket;

    String data[][]={ {"101","Amit","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"101","Sachin","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"102","Jai","670000","670000","670000","670000","670000"},
            {"101","Sachin","670000","670000","670000","670000","670000"}
    };
    String column[]={"Employee ID","First Name","Last Name", "Email", "Title"};

    public ReadEmployees(Socket socket,JFrame closableFrame) throws IOException {
        this.socket = socket;
        this.createUI(closableFrame);
    }

    public void createUI(JFrame closableFrame) throws IOException {
        Container container = new Container();
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);
        main.setBorder(BorderFactory.createEmptyBorder(30,40,20,20));

        JPanel header = new JPanel();
        header.setBackground(Color.white);

        JLabel headline = new JLabel("Employees");
        headline.setFont(new Font("Montserrat", Font.BOLD, 25));
        headline.setForeground(new Color(53,32,88));

        JButton newEmpButton = new JButton("+  New") {
            {
                setSize(130, 40);
                setMaximumSize(getSize());
            }
        };
        newEmpButton.setBackground(new Color(240,240,240));
        newEmpButton.setBorder(
                BorderFactory.createMatteBorder(7,30,7,30,new Color(240,240,240)));
        newEmpButton.setFont(new Font("Montserrat", Font.PLAIN, 18));

        header.add(headline);
        header.add(Box.createRigidArea(new Dimension(550,0)));
        header.add(newEmpButton);

        main.add(header);

        JTable table = new JTable();
        table.setBorder(BorderFactory.createEmptyBorder(10,10,20,0));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(45);
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
            model.addRow(new Object[]{data[i][0],data[i][1],data[i][2],data[i][3],data[i][4]});
        }
        /*
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(new Color(53,32,88));
        tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
        tableHeader.setForeground(Color.white);
        tableHeader.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        tableHeader.setPreferredSize(new Dimension(100, 32));*/

        main.add(new JScrollPane().add(table));
        main.setBounds(0, 0, 980, 600);

        container.add(main);
        new Layout(container,"Employees",this.socket);

        newEmpButton.addActionListener(actionEvent -> {
            try {
                closableFrame.dispose();
                new UpdateEmployee(this.socket,closableFrame);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }
}
