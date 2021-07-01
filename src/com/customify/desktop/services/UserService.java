package com.customify.desktop.services;

import com.customify.cli.SendToServer;
import com.customify.cli.data_format.UpdateCustomerFormat;
import com.customify.desktop.data_formats.user.UpdateUserFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class UserService {

    private Socket socket;
    public UserService() {
    }

    public UserService(Socket socket) {
        this.socket = socket;
    }

    public int update(UpdateUserFormat format) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
         return   this.handleUpdateUser();
        } else {
            System.out.println("\n\nError occurred when trying to send request to server\n");
        return 501;
        }
    }

    public int handleUpdateUser() throws IOException, ClassNotFoundException {
        try {
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.get(0));
            return jsonNode.get("status").asInt();
        } catch (Exception e) {
            System.out.println("RESPONSE ERROR =>" + e.getMessage());
            return 501;
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
