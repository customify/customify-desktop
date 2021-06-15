package com.customify.server.services;



import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.SendToClient;
import com.customify.server.response_data_format.employee.CreateFormat;
import com.customify.server.response_data_format.employee.GetAll;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    List<String> responseData = new ArrayList<>();
    ObjectOutputStream objectOutput;
    Socket socket;
    OutputStream output;
    String json_data;


    private int statusCode;

    public EmployeeService(Socket socket, String json_data) throws IOException, SQLException {
        this.json_data = json_data;
        this.socket = socket;
        System.out.println(socket);

    }

    public EmployeeService(Socket socket) {
        this.socket = socket;
    }

    public void create(String json_data) throws SQLException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json_data);
        String email = jsonNode.get("email").asText();
        String firName = jsonNode.get("firName").asText();
        String lasName = jsonNode.get("lasName").asText();
        String title = jsonNode.get("title").asText();
        String password = jsonNode.get("password").asText();
//        String bussiness_id = jsonNode.get("bussiness_id").asText();

        Connection connection = Db.getConnection();

        String query = "INSERT INTO EMployee (emp_id,firName,lasName,email,title,password) VALUES(?,?, ?, ?, ?, ?)";
        CreateFormat format;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 34333);
            statement.setString(2, firName);
            statement.setString(3, lasName);
            statement.setString(4, email);
            statement.setString(5, title);
            statement.setString(6 , password);

            int i = statement.executeUpdate();
            if (i > 0) {
//                System.out.println("success");
                format = new CreateFormat("Successfully registered an Employee",201);
                format.setJson_data(json_data);


            } else {
                System.out.println("stuck somewhere");
                format = new CreateFormat("STUCK SOMEWHERE",201);

            }

        } catch (Exception e)
        {
            format = new CreateFormat("STUCK SOMEWHERE",201);
            String response_json = objectMapper.writeValueAsString(format);
            responseData.add(response_json);
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        } finally{
            SendToClient serverResponse =new SendToClient(this.socket,this.responseData);
        }
    }
    public void readOne() throws SQLException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(this.json_data);
        String received_code = jsonNode.get("employeeId").asText();
        int i = Integer.parseInt(received_code.trim());

        Connection connection = Db.getConnection();
        String query = "SELECT * FROM Employee WHERE emp_id ="+ i;
        String firstName, lastName, email, title;
        int employeeId;
        int state;


        String json = "";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);


            if (!rs.next()) {

                json = "{ \"status\" : \"404\"}";
                responseData.add(json);
            } else {
                employeeId = rs.getInt("emp_id");
                firstName = rs.getString("firname");
                lastName = rs.getString("lasname");
                email = rs.getString("email");
                title = rs.getString("title");
                GetAll format = new GetAll(firstName, lastName, email, employeeId , title, 200);
                json = objectMapper.writeValueAsString(format);
                responseData.add(json);
            }



        } catch (Exception ex) {
            System.out.println("DB-ERROR " + ex.getMessage());
            json = "{ \"status\" : \"500\"}";
            responseData.add(json);
        } finally {
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(this.responseData);
        }

    }
    public void readAll() throws SQLException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Connection connection = Db.getConnection();
        String query = "SELECT * FROM Employee";
        String firstName, lastName, email, title;
        int employeeId;
        String json = "";

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (!rs.next()) {
                System.out.println("NO EMPLOYEES FOUND");
                json = "{ \"status\" : \"404\"}";
                responseData.add(json);
            } else {
                while (rs.next()) {
                    employeeId = rs.getInt("emp_id");
                    firstName = rs.getString("firname");
                    lastName = rs.getString("lasname");
                    email = rs.getString("email");
                    title = rs.getString("title");
                    GetAll format = new GetAll(firstName, lastName, email, employeeId , title, 200);
                    json = objectMapper.writeValueAsString(format);

                    responseData.add(json);
                }

            }
        } catch (Exception ex) {
            System.out.println("DB-ERROR " + ex.getMessage());
            json = "{ \"status\" : \"500\"}";
            responseData.add(json);
        } finally {
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);


            objectOutput.writeObject(this.responseData);
        }
    }


    public void update(String data) throws SQLException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);

        OutputStream output = this.socket.getOutputStream();
        ObjectOutputStream objectOutput =  new ObjectOutputStream(output);

        Statement stmt = null;
        Connection connection = null;

        try {
            connection = Db.getConnection();

            // System.out.println("Creating statement...");
            stmt = connection.createStatement();

            String sql = "UPDATE customers SET customer_code = "+jsonNode.get("customer_code").asText()+",email = "+jsonNode.get("email").asText()+
                    ",firstName="+jsonNode.get("firstName").asText()+",lastName="+jsonNode.get("lastName").asText()+", WHERE customer_code = "+jsonNode.get("customer_code").asText();

            stmt.executeUpdate(sql);

            stmt.close();
            connection.close();
        }
        catch (Exception e){

        }
    }

}

