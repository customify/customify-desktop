package com.customify.desktop.plans.ui;

import com.customify.desktop.SendToServer;
import com.customify.desktop.plans.model.Plans;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;

public class PlanService {
    public final Socket socket;
    private Plans plans;

    public PlanService(Socket socket,Plans format){

        this.socket = socket;
        this.plans = format;
    }
    public void create() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(this.plans);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("data was sent to server");
//            this.handleResponse("create");
        } else {
            System.out.println("Failed to send the request on the server ....");
        }
    }


}
