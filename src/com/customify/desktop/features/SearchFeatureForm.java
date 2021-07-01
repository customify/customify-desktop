package com.customify.desktop.features;
import com.customify.server.models.billing.Feature;
import com.customify.server.services.billing.FeatureDao;

import java.awt.*;
import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;
        import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;



public class SearchFeatureForm extends JPanel implements ActionListener{
    private ArrayList<Feature> listFeature;
    private ArrayList<JButton> listEdit, listDelete;
    private JTextField txtKey;
    private JButton btnSearch,newButton;
    private JTable tblResult;

    public SearchFeatureForm(){
//        super("Search Feature");
        listFeature = new ArrayList<Feature>();
        listEdit = new ArrayList<JButton>();
        listDelete = new ArrayList<JButton>();



        JPanel pnMain = new JPanel();
        pnMain.setSize(this.getSize().width-5, this.getSize().height-20);
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.Y_AXIS));

        JPanel pnew = new JPanel();
        pnew.setLayout(new BoxLayout(pnew,BoxLayout.X_AXIS));
        pnew.setSize(this.getSize().width-5, 140);

        JLabel heading = new JLabel("Billing Features");
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setBounds(30, 42, 700, 150);
        heading.setForeground(new Color(53, 32, 88));
        pnew.add(heading);

        newButton = new JButton("+ New");
        newButton.setBounds(700,50,90, 65);
        newButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newButton.setForeground(new Color(53, 32, 88));
        newButton.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
        newButton.setBackground(Color.white);
        pnew.setSize(new Dimension(800, 100));
        pnew.add(newButton);



        JPanel pn1 = new JPanel();
        pn1.setLayout(new BoxLayout(pn1,BoxLayout.X_AXIS));
        pn1.setSize(this.getSize().width-5, 70);
        pn1.add(new JLabel("Keyword: "));
        txtKey = new JTextField();

        pn1.add(txtKey);
        btnSearch = new JButton("Search ");
        btnSearch.addActionListener(this);
        newButton.addActionListener(this);
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSearch.setForeground(Color.white);
        btnSearch.setBorder(BorderFactory.createLineBorder(new Color(53, 32, 88),1));
        btnSearch.setBackground(new Color(53, 32, 88));

        pn1.add(btnSearch);
        pnMain.add(pnew);
        pnMain.add(pn1);

        JPanel pn2 = new JPanel();
        pn2.setLayout(new BoxLayout(pn2,BoxLayout.Y_AXIS));
        tblResult = new JTable(new FeatureTableModel());
        JScrollPane scrollPane= new  JScrollPane(tblResult);
        tblResult.setFillsViewportHeight(false);
        scrollPane.setPreferredSize(new Dimension(900, 250));

        tblResult.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        tblResult.setShowGrid(false);
        tblResult.setIntercellSpacing(new Dimension(0, 1));
        tblResult.setRowHeight(40);
        btnSearchClick();
        ((DefaultTableModel)tblResult.getModel()).fireTableDataChanged();

        tblResult.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? new Color(253, 249, 249) : new Color(240, 240, 240));
                return c;
            }
        });

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tblResult.getColumn("Edit").setCellRenderer(buttonRenderer);
        tblResult.getColumn("Delete").setCellRenderer(buttonRenderer);
        tblResult.addMouseListener(new JTableButtonMouseListener(tblResult));

        JTableHeader tableHeader = tblResult.getTableHeader();
        tableHeader.setBackground(new Color(53, 32, 88));
        tableHeader.setFont(new Font("Montserrat", Font.BOLD, 13));
        tableHeader.setForeground(Color.white);
        tableHeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        tableHeader.setPreferredSize(new Dimension(100, 32));

        pn2.add(scrollPane);
        pnMain.add(pn2);
        this.add(pnMain);
        pnMain.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(btnSearch)){
            btnSearchClick();
            return;
        }
        if(btnClicked.equals(newButton)){
            btnNewClick(listFeature.size()-1);
            return;
        }

        for(int i=0; i<listEdit.size(); i++)
            if(btnClicked.equals(listEdit.get(i))){
                btnEditClick(i);
                return;
            }
        for(int i=0; i<listDelete.size(); i++)
            if(btnClicked.equals(listDelete.get(i))){
                btnDeleteClick(i);
                return;
            }
    }



    /**
     * processing the event that the Search button is clicked
     */
    private void btnSearchClick(){
//        if((txtKey.getText() == null)||(txtKey.getText().length() == 0))
//            return;
        FeatureDao featureDao = new FeatureDao();
        listFeature = featureDao.searchFeature(txtKey.getText().trim());
        listEdit.clear();
        listDelete.clear();
        for(int i=0; i<listFeature.size(); i++){
            JButton btn = new JButton("Edit");
            btn.addActionListener(this);
            listEdit.add(btn);
            btn = new JButton("Delete");
            btn.addActionListener(this);
            listDelete.add(btn);

        }
        ((DefaultTableModel)tblResult.getModel()).fireTableDataChanged();
    }

    /**
     * processing the event that the @index Edit button is clicked
     * @param index
     */
    private void btnEditClick(int index){
        (new EditFeatureForm(this, listFeature.get(index), index)).setVisible(true);
    }
    /**
     * processing the event that the  new button is clicked
     * @param index
     */
    private void btnNewClick(int index){
        (new NewFeatureForm(this, index)).setVisible(true);
    }
    /**
     * enable to refresh the result table after update a feature from @Update form
     * @index: the index of updated feature in the list
     * @Feature: the updated feature
     */
    public void refreshResultAfterUpdate(int index, Feature feature){
        listFeature.remove(index);
        listFeature.add(index, feature);
        ((DefaultTableModel)tblResult.getModel()).fireTableDataChanged();
    }

    /**
     * processing the event that the @index Delete button is clicked
     * @param index
     */
    private void btnDeleteClick(int index){
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (this, "Would you like to delete this feature?", "Warning", dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            FeatureDao featureDao = new FeatureDao();
            featureDao.deleteFeature(listFeature.get(index).getId());
            listFeature.remove(index);
            listEdit.remove(index);
            listDelete.remove(index);
        }
        ((DefaultTableModel)tblResult.getModel()).fireTableDataChanged();
    }

    class FeatureTableModel extends DefaultTableModel {
        private String[] columnNames = {"Id", "Name", "Description", "Edit", "Delete"};
        private final Class<?>[] columnTypes = new Class<?>[] {Integer.class, String.class, String.class, String.class,
                String.class, String.class, JButton.class,  JButton.class};

        @Override public int getColumnCount() {
            return columnNames.length;
        }

        @Override public int getRowCount() {
            return listFeature.size();
        }

        @Override public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override public Class<?> getColumnClass(int columnIndex) {
            return columnTypes[columnIndex];
        }

        @Override public Object getValueAt(final int rowIndex, final int columnIndex) {
            /*Adding components*/
            switch (columnIndex) {
                case 0:
                    return listFeature.get(rowIndex).getId();
                case 1:
                    return listFeature.get(rowIndex).getName();
                case 2:
                    return listFeature.get(rowIndex).getDescription();
                case 3:
                    return listEdit.get(rowIndex);
                case 4:
                    return listDelete.get(rowIndex);
                default: return "Error";
            }
        }
    }

    class JTableButtonMouseListener extends MouseAdapter {
        private final JTable table;

        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
            int row    = e.getY()/table.getRowHeight(); //get the row of the button

            //*Checking the row or column is valid or not
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    //perform a click event
                    ((JButton)value).doClick();
                }
            }
        }
    }

    class JTableButtonRenderer implements TableCellRenderer {
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                                 boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            button.setFont(new Font("Tahoma", Font.PLAIN, 14));
            button.setBounds(830, 530, 90, 35);
            button.setBackground(Color.white);
            button.setForeground(new Color(53, 32, 88));
            return button;
        }
    }

    /**
     * @param args
     */
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        SearchFeatureForm myFrame = new SearchFeatureForm();
//        myFrame.setSize(600,300);
//        myFrame.setVisible(true);
//        myFrame.setLocation(200,10);
//    }
}