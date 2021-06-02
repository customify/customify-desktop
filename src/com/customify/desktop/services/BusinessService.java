/**
 * @description
 * The register business front-end services class this is here to
 * register all the businesses in the project must use this service
 * register and edit businesses,
 * reading businesses and deleting them.
 *
 * @author IRUMVA HABUMUGISHA Anselme, Kellia Umuhire
 * @version 1
 * @since Wednesday, 3 February 2021 - 08:17 - Time in Nyabihu
 * */

package com.customify.desktop.services;

import com.customify.desktop.SendToServer;
import com.customify.desktop.data_formats.business.BusinessFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class BusinessService {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;
    private List<String> response;

    public BusinessService() {
    }

    /**
     * Class Constructor
     *
     * @author IRUMVA HABUMUGISHA Anselme
     * @role Constructor it assigns socket to the variable socket
     */
    public BusinessService(Socket socket) {
        this.socket = socket;
    }

    /**
     * @param businessFormat the business detains in form of a format
     * @author IRUMVA HABUMUGISHA Anselme
     * @role this function is to create a new business
     * We send the request to the backend
     */
    public void create(BusinessFormat businessFormat) throws IOException, ClassNotFoundException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(businessFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            this.handleResponse("create");
        } else {
            System.out.println("Failed to send the request on the server ....");
        }
    }

    /**
     * @param businessFormat the business detains in form of a format
     * @author IRUMVA HABUMUGISHA Anselme
     * @role this function is to edit an existing business
     * We send the request to the backend
     */
    public void update(BusinessFormat businessFormat) throws IOException, ClassNotFoundException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(businessFormat);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            this.handleResponse("update");
        } else {
            System.out.println("The request is not sent to the server ....");
        }
    }

    /**
     * @author Kellia Umuhire
     * @param json Object key to send to the server
     * @role this function is for getting all business
     */
    public void getBusinesses(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleGetResponse();
        } else {
            System.out.println("Request failed...");
        }
    }

    /**
     * this function is for handling the response after fetching all the businesses from the server
     * and displaying the response
     * @author Kellia Umuhire
     */
    public void handleGetResponse() throws IOException, ClassNotFoundException {
        //Get response
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        ObjectMapper objectMapper = new ObjectMapper();

        //Casting the response data to list
        List<String> data = (List<String>) this.objectInput.readObject();

        if (data.get(0) == "500") System.out.println("An error occurred");
    }

    /**
     * @author Kellia Umuhire
     * @param json Object holding businessId and key to send to the server
     * @role method for getting one business by its id
     */
    public void getById(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            handleResponse("getbyid");
        }
    }

    /**
     * @author Kellia Umuhire
     * @param json Object holding businessId and key to send to the server
     * @role Method for sending a delete request to the server
     */
    public void deleteBusiness(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            handleResponse("delete_business");
        } else {
            System.out.println("An error occurred");
        }
    }

    /**
     * @author Kellia Umuhire, IRUMVA HABUMUGISHA Anselme
     * @param func_name the name of the function to pass the response to
     * @role General method for handling response from the server
     */
    public void handleResponse(String func_name) throws ClassNotFoundException {
        try {
            this.input = this.socket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);
            this.json_data = (String) this.objectInput.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json_data);
            System.out.println(this.json_data);
        } catch (IOException e) {
            System.out.println("Error in reading Object " + e.getMessage());
        }
    }
}
