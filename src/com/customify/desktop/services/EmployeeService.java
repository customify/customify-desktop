package com.customify.desktop.services;

import com.customify.desktop.SendToServer;
import com.customify.desktop.data_formats.employee.EmployeeDataFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class EmployeeService {
    public final Socket socket;

    public EmployeeService(Socket socket) {
        this.socket = socket;
    }

    public void updateEmployee(EmployeeDataFormat format){
    }
    public int createEmp(EmployeeDataFormat format) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleCreateResponse();
        }
        return 0;
    }
    public void handleCreateResponse() throws IOException, ClassNotFoundException {
        try {
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();

            String json_response = response.get(0);
            System.out.println("HERE'S THE RESPONSE FROM THE SERVER => " + json_response);

        } catch (Exception e) {
            System.out.println("RESPONSE ERROR =>" + e.getMessage());
        }
    }

}
