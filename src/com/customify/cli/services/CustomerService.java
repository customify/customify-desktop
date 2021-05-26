package com.customify.cli.services;

import com.customify.cli.Colors;
import com.customify.cli.Keys;
import com.customify.cli.SendToServer;
import com.customify.cli.data_format.CreateCustomerFormat;
import com.customify.cli.data_format.DeActivateCustomer;
import com.customify.cli.data_format.GetCustomer;
import com.customify.cli.data_format.UpdateCustomerFormat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private Socket socket;
    private ObjectMapper objectMapper;

    public CustomerService() {
    }

    public CustomerService(Socket socket) {
        this.socket = socket;
    }


    public int create(CreateCustomerFormat format) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        int resp;

        if (serverSend.send())
            resp =  this.handleCreateCustomerResponse();
        else
            resp= 500;
        return resp;
    }


    /**
     * @author SAMUEL Dushimimana
     * @role this function is to handle response on the successfully registration of the customer
     */
    public int handleCreateCustomerResponse() throws IOException, ClassNotFoundException {
        int resp;
        try {
            InputStream inputStream = this.socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<String> response = (List<String>) objectInputStream.readObject();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.get(0));
            resp = jsonNode.get("status").asInt();
        } catch (Exception e) {
            resp = 500;
            System.out.println("RESPONSE ERROR =>" + e.getMessage());
        }
        return resp;
    }

    public Socket getSocket() {
        return socket;
    }

    /**
     * @author Nshimiye Emmy
     * @role this service method is to update the customer on client side
     */
    public void handleUpdateCustomerSuccess() throws IOException, ClassNotFoundException {
        InputStream inputStream = this.getSocket().getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        InputStream inputStream = this.getSocket().getInputStream();
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        try {
//            List<Response> response = (List<Response>) objectInputStream.readObject();
//            System.out.println("Status: "+ response.get(0).getStatusCode());
//            if(response.get(0).getStatusCode() == 200){
//                UpdateCustomerFormat updatedCustomer = (UpdateCustomerFormat) response.get(0).getData();
//
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println("\t\t Customer Updated successfully");
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//            }
//            else if(response.get(0).getStatusCode() == 400){
//                System.out.println("\n\nInvalid Customer format.Please enter Customer details as required\n\n");
//            }
//            else{
//                System.out.println("\n\nUnknown error occurred.\n");
//            }
//
//        } catch (IOException e) {
//            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
//        } catch (ClassNotFoundException e) {
//            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
//        }

        return;
    }

    /**
     * @author Nshimiye Emmy
     * @role this service method is to update the customer on client side
     */
    public void update(UpdateCustomerFormat format) throws IOException, ClassNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        if (serverSend.send()) {
            this.handleUpdateCustomerSuccess();
        } else {
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }


    /**
     * @author Murenzi Confiance Tracy
     * @role this function is to handle response on the successfully disabled customer
     */
    public void disable(String json) throws IOException, ClassNotFoundException {
        try {

            SendToServer serverSend = new SendToServer(json, this.socket);
            if (serverSend.send()) {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                List<String> res = (List) objectInput.readObject();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 200) {
                    System.out.println(Colors.ANSI_PURPLE);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t          CARD WAS SUCCESSFULLY DE-ACTIVATED!!!!");
                    System.out.println(Colors.ANSI_RESET);
                } else if (jsonNode.get("status").asInt() == 404) {
                    System.out.println(Colors.ANSI_PURPLE);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t        THE CUSTOMER DOESN'T EXIST");
                    System.out.println(Colors.ANSI_RESET);
                } else if (jsonNode.get("status").asInt() == 444) {
                    System.out.println(Colors.ANSI_PURPLE);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t        NO RESPONSE");
                    System.out.println(Colors.ANSI_RESET);
                }
                else {
                    System.out.println(Colors.ANSI_PURPLE);
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t        UNKNOWN CODE");
                    System.out.println(Colors.ANSI_RESET);
                }
            }

        } catch (Exception e) {
            System.out.println("Exception Caught ");
        }
        return;
    }

    /**
     * @author Murenzi Confiance Tracy
     * @role this function is to handle response on the successfully activated customer
     */

    public Object reEnableCard(String code) throws IOException, ClassNotFoundException {
        DeActivateCustomer format = new DeActivateCustomer(code);
        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(format);
        SendToServer sendToServer = new SendToServer(request, socket);

        if (sendToServer.send()) {
//                System.out.println("\t\tCard was activated successfully\n");
            InputStream input = this.socket.getInputStream();
            ObjectInputStream objectInput = new ObjectInputStream(input);
            List<String> res = (List<String>) objectInput.readObject();

            String responseData = res.get(0);
//            System.out.println("Response: " + responseData);
            JsonNode jsonNode = new ObjectMapper().readTree(responseData);

            if (jsonNode.get("status").asInt() == 200) {
                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t          CARD WAS SUCCESSFULLY ACTIVATED!!!!");
                System.out.println(Colors.ANSI_RESET);
            } else if (jsonNode.get("status").asInt() == 400) {
                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t THE CUSTOMER DOESN'T EXIST");
                System.out.println(Colors.ANSI_RESET);
            } else if (jsonNode.get("status").asInt() == 500) {
                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t  SYSTEM ERROR OCCURRED");
                System.out.println(Colors.ANSI_RESET);
            } else {
                System.out.println(Colors.ANSI_PURPLE);
                System.out.println("\t\t\t\t\t\t\t\t\t\t   UNKNOWN ERROR");
                System.out.println(Colors.ANSI_RESET);
            }
        }
        return null;
    }

    public List getAll() throws IOException {
        String json = "{ \"key\" : \"" + Keys.GET_ALL_CUSTOMERS + "\"}";
        SendToServer serverSend = new SendToServer(json, this.socket);
        List<String> res = new ArrayList<>();
        if (serverSend.send()) {

            try {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                res = (List) objectInput.readObject();

                objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 500) {
                    System.out.println("\t\t\t\t---- INTERNAL SERVER ERROR -----");
                    return null;
                } else if (jsonNode.get("status").asInt() == 404) {
                    System.out.println("\n\t\t\t*******************************************************************************************************");
                    System.out.println("                                                 NO CUSTOMERS FOUND                                            ");
                    System.out.println("\t\t\t*******************************************************************************************************");
                    return null;
                }


            } catch (Exception e) {
                System.out.println("RESPONSE ERROR" + e.getMessage());
            }
        }
        return res;

    }

    public List get(GetCustomer format) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(format);
        SendToServer serverSend = new SendToServer(json, this.socket);
        List<String> res = new ArrayList<>();
        if (serverSend.send()) {
            try {
                InputStream input = this.socket.getInputStream();
                ObjectInputStream objectInput = new ObjectInputStream(input);
                res = (List) objectInput.readObject();
                JsonNode jsonNode = objectMapper.readTree(res.get(0));

                if (jsonNode.get("status").asInt() == 404) {
                    System.out.println("\n\t\t\t*******************************************************************************************************");
                    System.out.println("                                                  NO CUSTOMER FOUND                                            ");
                    System.out.println("\t\t\t*******************************************************************************************************");
                    return null;
                }

            } catch (Exception e) {
                System.out.println("RESPONSE ERROR HERE" + e.getMessage());
            }
        }
        return res;
    }
}