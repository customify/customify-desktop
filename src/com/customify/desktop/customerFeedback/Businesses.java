package com.customify.desktop.customerFeedback;

import com.customify.cli.Keys;
import com.customify.cli.services.CustomerFeedbackService;
import com.customify.desktop.utils.interfaces.SelectBusinessFormat;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Businesses {
    private Socket socket;

    public Businesses(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket() {

    }
    public SelectBusinessFormat viewAll()throws IOException,ClassNotFoundException{
        String json = "{ \"key\" : \""+ Keys.GET_BUSINESS_NAME +"\" }";
        CustomerFeedbackService feedService = new CustomerFeedbackService(socket);
        return feedService.getBusinesses(json);
    }
}
