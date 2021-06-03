package com.customify.desktop.employee;

import com.customify.cli.utils.authorization.structure.EmployeeUser;
import com.customify.desktop.Keys;
import com.customify.desktop.data_formats.employee.EmployeeDataFormat;
import com.customify.desktop.layout.Layout;
import com.customify.desktop.services.EmployeeService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;

public class UpdateEmployee extends JPanel {
    private final Socket socket;
    public UpdateEmployee(Socket socket) {
        this.socket = socket;
    }

    public JPanel init(){
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(Color.white);

        JPanel header = new JPanel();
        JLabel headline = new JLabel("Update employee ");
        headline.setPreferredSize(new Dimension(300, 100));
        headline.setFont(new Font("Montserrat", Font.BOLD, 29));
        headline.setForeground(new Color(53,32,88));
        header.setBackground(Color.white);

        main.add(header);

        InputFactory factory = new InputFactory();

        JTextField firstNameField = factory.createInput();
        main.add(factory.createPanel("First name",firstNameField));

        JTextField lastNameField = factory.createInput();
        main.add(factory.createPanel("Last name",lastNameField));

        JTextField emailField = factory.createInput();
        main.add(factory.createPanel("Email",emailField));

        JTextField titleField = factory.createInput();
        main.add(factory.createPanel("Title",titleField));

        JTextField usernameField = factory.createInput();
        main.add(factory.createPanel("Password",usernameField));

        JPanel buttonGroup = new JPanel();
        buttonGroup.setBackground(Color.white);

        JButton cancel = new JButton("Discard");
        cancel.setBounds(400,400,180,40);
        cancel.setBackground(Color.white);
        cancel.setBorder(BorderFactory.createCompoundBorder(
                cancel.getBorder(),
                BorderFactory.createEmptyBorder(7, 30, 7, 30)));
        cancel.setFont(new Font("Montserrat", Font.PLAIN, 18));

        JButton btn = new JButton("Save");
        btn.setBounds(1020,400,180,40);
        btn.setBackground(new Color(53,32,88));
        btn.setForeground(Color.white);
        btn.setBorder(BorderFactory.createCompoundBorder(
                btn.getBorder(),
                BorderFactory.createEmptyBorder(7, 30, 7, 30)));
        btn.setFont(new Font("Montserrat", Font.PLAIN, 18));

        btn.addActionListener(actionEvent -> {
            EmployeeUser employee = new EmployeeUser();
            /*try {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }*/
        });

        buttonGroup.add(cancel);
        buttonGroup.add(btn);

        header.add(headline);
        main.add(buttonGroup);

        main.setBounds(90, 40, 800, 550);

        return main;
    }
    public void update(String firstName,String lastName,String email,String title) throws IOException {
        EmployeeDataFormat format = new EmployeeDataFormat(firstName,lastName,email,title,5);
        format.setKey(Keys.UPDATE_EMPLOYEE);

        EmployeeService service = new EmployeeService(socket);
        service.updateEmployee(format);
    }

    public static void main(String[] args) throws IOException {
        Container container = new Container();
//        container.add(new UpdateEmployee().init());
        new Layout(container,"Update Employee");
    }
}
class InputFactory{
    public JTextField createInput(){
        JTextField textField = new JTextField("", 20);
        textField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.black, 1, true),
                BorderFactory.createEmptyBorder(2, 2, 2, 2))
        );
        textField.setFont(new Font("Montserrat", Font.PLAIN, 18));
        textField.setBounds(0,0,30,10);
        return textField;
    }
    public JPanel createPanel(String placeholder,JTextField jTextField){
        JPanel container = new JPanel();
        container.setBackground(Color.white);

        JLabel placeholderText = new JLabel(placeholder);
        placeholderText.setFont(new Font("Montserrat", Font.PLAIN, 18));
        placeholderText.setBackground(Color.green);
        placeholderText.setPreferredSize(new Dimension(200, 30));

        container.add(placeholderText);
        container.add(jTextField);

        return container;
    }
}