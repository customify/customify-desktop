package com.customify.desktop.components.plan;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

import com.jgoodies.forms.layout.FormLayout;
import com.customify.desktop.layout.Layout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class PlanRead extends JPanel {
	Container container = new Container();

	/**
	 * Create the panel.
	 */
	public PlanRead() {}
	
	public Container init() {
		container.setBackground(Color.WHITE);
		container.setLayout(null);
		
		JButton btnNewButton = new JButton("Feature");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(395, 105, 120, 32);
		btnNewButton.setFont(new Font("Montserrat", Font.BOLD, 20));
		container.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("+ New");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(557, 105, 120, 32);
		btnNewButton_1.setFont(new Font("Montserrat", Font.BOLD, 20));
		container.add(btnNewButton_1);
		
		JTextArea plan = new JTextArea();
		plan.setText("Plan Read");
		plan.setBackground(new Color(53,32,88));
		plan.setBounds(89, 109, 203, 28);
		plan.setFont(new Font("Montserrat", Font.BOLD, 24));
		plan.setForeground(Color.WHITE);
		
		container.add(plan);
		
		JTable table = new JTable(6,4);
		table.setBorder(null);
		table.setBounds(89, 243, 588, 246);
		table.setRowHeight(35);
		
		container.add(table);
		
		JTable table_1 = new JTable(1,4);
		table_1.setRowHeight(35);
		table_1.setBackground(new Color(53,32,88));
		table_1.setBounds(89, 185, 588, 32);
		container.add(table_1);
		
		return container;
	}
	
	public static void main(String[] args) throws IOException {
        new Layout((new PlanRead().init()));
    }
}
