package com.customify.desktop.services;

import com.customify.cli.Keys;
import com.customify.desktop.SendToServer;
import com.customify.desktop.data_formats.employee.EmployeeDataFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

    public List getAll() throws IOException {
        String json = "{ \"key\" : \"" + Keys.GET_ALL_EMPLOYEES + "\"}";
        com.customify.cli.SendToServer serverSend = new com.customify.cli.SendToServer(json, this.socket);
        List<String> res = new ArrayList<>();
        if (serverSend.send()) {

            try {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                res = (List) objectInput.readObject();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 500) {
                    System.out.println("\t\t\t\t---- INTERNAL SERVER ERROR -----");
                    return null;
                } else if (jsonNode.get("status").asInt() == 404) {
                    System.out.println("\n\t\t\t*******************************************************************************************************");
                    System.out.println("                                                 NO EMPLOYEES FOUND                                            ");
                    System.out.println("\t\t\t*******************************************************************************************************");
                    return null;
                }


            } catch (Exception e) {
                System.out.println("RESPONSE ERROR" + e.getMessage());
            }
        }
        return res;

    }
}
