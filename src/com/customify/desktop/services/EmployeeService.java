package com.customify.desktop.services;

import com.customify.desktop.SendToServer;
import com.customify.desktop.data_formats.employee.EmployeeDataFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;

public class EmployeeService {
    public final Socket socket;

    public EmployeeService(Socket socket) {
        this.socket = socket;
    }

    public void updateEmployee(EmployeeDataFormat format) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer sendToServer = new SendToServer(json, this.socket);
        if (sendToServer.send()) {
            System.out.println("data was sent to server");
//            this.handleResponse("create");
        } else {
            System.out.println("Failed to send the request on the server ....");
        }
    }
}
