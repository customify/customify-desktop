/*
 * @author: NSENGIYUMVA Gershom
 *
 * @Description
 * This class has a query for inserting, fetching and deleting data to and from the database
 * done on 9 Feb 2021
 */
package com.customify.server.services;

import com.customify.server.CustomizedObjectOutputStream;
import com.customify.server.Db.Db;
import com.customify.server.data_format.business.BusinessRFormat;
import com.customify.server.data_format_CustomerFeedback.CustomerFeedbackDataFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerFeedbackService {
    Socket socket;
    OutputStream output;
    ObjectOutputStream objectOutput;
    String statusCode;

    public CustomerFeedbackService(Socket socket)throws IOException{
        this.socket = socket;
    }

    // function to send data to the database
    public void Feedback(String data) throws SQLException, JsonProcessingException {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(data);
            Connection connection = Db.getConnection();
            System.out.println(data);
            String feedbackQuery = "INSERT INTO CustomerFeedback(customer_name,business_id,title,description,created_date) values (?,?,?,?,NOW())";

            Statement stmt = Db.getStatement();
            ResultSet checkBusiness = stmt.executeQuery("SELECT * FROM businesses");

            // prepare the statement for the query
            while (checkBusiness.next()) {
                if (jsonNode.get("businessId").asInt() == checkBusiness.getInt("id")) {
                    PreparedStatement statement = connection.prepareStatement(feedbackQuery);
//                    statement.setInt(1, jsonNode.get("customerId").asInt());
                    statement.setString(1,jsonNode.get("customer_name").asText());
                    statement.setInt(2, jsonNode.get("businessId").asInt());
                    statement.setString(3, jsonNode.get("title").asText());
                    statement.setString(4, jsonNode.get("description").asText());

                    if(statement.execute()){
                        objectOutput.writeObject("{\"status\": 500}");
                        objectOutput.close();
                        System.out.println("Your query not working .... ");
                    }else{
                        objectOutput.writeObject("{\"status\": 200}");
                        objectOutput.close();
                        System.out.println("Query Ok !!! ");
                        break;
                    }
                } else {
                    System.out
                            .println("The business with id " + jsonNode.get("businessId").asInt() + " does not exist");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /*
     * The function for getting the customer feedback from the database
     */
    public void getAllFeedbacks(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);
        Statement stmt = Db.getStatement();
//        String query = "Select * from CustomerFeedback";
        String query = "SELECT c.customer_name,b.name, c.title,c.description,c.created_date FROM businesses b INNER JOIN CustomerFeedback c ON b.id = c.business_id WHERE c.business_id ="+ jsonNode.get("businessId").asInt();
        List<String> feedbacks = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                CustomerFeedbackDataFormat cf =
                        new CustomerFeedbackDataFormat(
                                rs.getString(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getDate(5).toString()
                        );

                String json = objectMapper.writeValueAsString(cf);
                feedbacks.add(json);
            }

            System.out.println(feedbacks);
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(feedbacks);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


//    helper business method
public void getAllBusinesses() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    Statement statement = Db.getStatement();
    String query = "Select name from businesses";
    List<String> alldata = new ArrayList<>();

    try {
        this.statusCode = "200";
        alldata.add(this.statusCode);
        ResultSet res = statement.executeQuery(query);
        String data;
        while(res.next()){

            BusinessRFormat bs = new BusinessRFormat(
                    res.getString(3)
            );
            data = objectMapper.writeValueAsString(bs);
            alldata.add(data);
        }
    }
    catch (Exception e){
        this.statusCode = "500";
        alldata.add(this.statusCode);
    }
    finally{
        this.output = socket.getOutputStream();
        this.objectOutput = new CustomizedObjectOutputStream(this.output);
        objectOutput.writeObject(alldata);
    }

}

    /*
     * Here is the function for deleting the customer feedback from the database
     */

    public void deleteCustomerFeedback(String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(data);
        Statement stmt = Db.getStatement();
        String json="";
        System.out.println(data);
        try {
            int deleteQuery = stmt.executeUpdate(
                    "DELETE FROM CustomerFeedback WHERE feedback_id = " + jsonNode.get("feedbackId").asInt());

            if (deleteQuery == 1) {
                json = "{\"message\" : \"" + "Successfully deleted" + "\", \"statusCode\" : \"" + 200 + "\" }";
//                objectOutput.writeObject(json);
            }
            this.output = socket.getOutputStream();
            this.objectOutput = new CustomizedObjectOutputStream(this.output);
            objectOutput.writeObject(json);
        }catch (SQLException e){
            json = "{\"message\" : \""+e.getMessage()+"\", \"statusCode\" : \""+ 400 +"\" }";
        }
    }
}
