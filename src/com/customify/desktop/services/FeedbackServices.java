package com.customify.desktop.services;

import com.customify.cli.SendToServer;
import com.customify.desktop.data_formats.Customer_feedback.CustomerFeedbackFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;


/**
 * Author: Gershom Nsengiyumva
 * done on:  May 29 2021
 *
 * This is is the service class for the customers to provide the feedbacks
 * for the services they got from various businesses.
 *
 * */


public class FeedbackServices {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;

    public FeedbackServices(Socket socket) {
        this.socket = socket;
    }

    /**
     * @author Gershom
     * This is the function for sending customer feedbac data to the server.
     * @param format
     */
    public void Feedback(CustomerFeedbackFormat format) throws IOException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(format);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("\nFeeback Sent\n");
        } else {
            System.out.println("Failed to send the feedback data!! You need to check well");
        }
    }

    public void getBusinesses(String json) throws IOException, ClassNotFoundException {
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleGetResponse();
        } else {
            System.out.println("Request failed...");
        }
    }

    public void handleGetResponse() throws IOException, ClassNotFoundException {
        this.input = this.socket.getInputStream();
        this.objectInput = new ObjectInputStream(this.input);
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> data = (List<String>) this.objectInput.readObject();

        if(data.get(0)=="500") System.out.println("An error occurred");
        else {

            System.out.format("%20s\n","Name");
            for (int i = 1; i < data.size(); i++) {
                JsonNode bs = objectMapper.readTree(data.get(i));
                System.out.format("%10s\n", bs.get("name").asText());
            }
        }
    }
}