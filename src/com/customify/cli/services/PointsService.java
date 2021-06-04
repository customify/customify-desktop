/**
 @author GISA KAZE Fredson
 Date: 20/05/2021
 */
package com.customify.cli.services;
import com.customify.cli.Colors;
import com.customify.cli.SendToServer;
import com.customify.cli.data_format.GetWinnersDataFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
public class PointsService {
    private Socket socket;
    InputStream inputStream;
    ObjectInputStream objectInputStream;
    public PointsService(Socket socket){ }
//    public PointsService(Socket socket) {
//        this.socket = socket;
//    }
    public void getWinners() {
        try {
            GetWinnersDataFormat data =  new GetWinnersDataFormat();
            ObjectMapper objectMapper = new ObjectMapper();
            String request = objectMapper.writeValueAsString(data);
            SendToServer sendToServer = new SendToServer(request,socket);
//            if ..... request ........
            if (sendToServer.send()) {
                this.getAllWinnersSuccess();
            }
            else{
                System.out.println("\nError occurred when sending request to server\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void getAllWinnersSuccess() throws IOException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<String> response = (ArrayList<String>) objectInputStream.readObject();
            if(response.size() == 0 ) {
                System.out.println("\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tNo winners to show\n\n");
                return;
            }
            System.out.println("\n\n");
            this.Header();
            System.out.println(Colors.ANSI_GREEN);
            System.out.format("\t%-15s%-25s%-25s%-30s%-10s%-30s%-10s", "Customer ID", "First Name", "Last Name", "Email", "Points", "Winning date", "Customer code");
            System.out.println(Colors.ANSI_RESET);
            for (int i = 0; i < response.size(); i++) {
                JsonNode node = objectMapper.readTree(response.get(i));
                System.out.format("\t%-15s%-25s%-25s%-30s%-10s%-30s%-10s\n", node.get("customerId").asText(),node.get("firstName").asText(), node.get("lastName").asText(), node.get("email").asText(), node.get("noPoints").asDouble(), node.get("winingDate").asText(), node.get("code").asText());
            }
            System.out.println("\n\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public void Header(){
        System.out.println(Colors.ANSI_CYAN);
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWINNERS");
        System.out.println(Colors.ANSI_RESET);
    }
    public List<String> getWinnersUi(Socket socket) throws IOException {
        System.out.println("Socket test..."+socket.getPort());
//        InputStream inputStream = socket.getInputStream();
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        System.out.println("in get winer");

        try {
            GetWinnersDataFormat data =  new GetWinnersDataFormat();
            ObjectMapper objectMapper = new ObjectMapper();
            String request = objectMapper.writeValueAsString(data);
            SendToServer sendToServer = new SendToServer(request,socket);
//            if ..... request ........
            if (sendToServer.send()) {
                inputStream = socket.getInputStream();
                objectInputStream = new ObjectInputStream(inputStream);
                List<String> response = (ArrayList<String>) objectInputStream.readObject();
                return response;
            }
            else{
                System.out.println("\nError when sending request to the server\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}