package com.customify.desktop.services;

import com.customify.cli.SendToServer;
import com.customify.server.Db.Db;
import com.customify.desktop.data_formats.Customer_feedback.CustomerFeedbackFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
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
    public void Feedback(CustomerFeedbackFormat format) throws IOException, ClassNotFoundException {
        var mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(format);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("\nFeeback Sent\n");
        } else {
            System.out.println("Failed to send the feedback data!! You need to check well");
        }
    }
}