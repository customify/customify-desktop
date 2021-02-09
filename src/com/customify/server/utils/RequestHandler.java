package com.customify.server.utils;

import com.customify.server.services.BusinessService;
import com.customify.server.Keys;
import com.customify.server.controllers.AuthController;
import com.customify.server.controllers.FeedbackController;
import com.customify.server.services.CustomerService;
import com.customify.server.services.BusinessService;
import com.customify.server.services.ProductService;
import com.customify.shared.Request;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;


public class RequestHandler {

    private final Socket clientSocket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private Keys key;
    private String json_data;

    public RequestHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void init() {
        try {
            this.input = this.clientSocket.getInputStream();
            this.objectInput = new ObjectInputStream(this.input);


                while(true) {
                    try {
                        List<String> clientRequest = (List)this.objectInput.readObject();
                        this.json_data = (String)clientRequest.get(0);
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(json_data);
                        this.key = Keys.valueOf(jsonNode.get("key").asText());

                        this.handleRequest();
                    } catch (Exception var5) {
                    }
                }

        } catch (IOException e) {
            System.out.println("Error in reading Object " + e.getMessage());
        }
    }

    public void handleRequest() throws IOException, SQLException {
        AuthController authController;
        CustomerService  customer = new CustomerService(this.clientSocket,this.json_data);
//        ProductController productController = new ProductController(this.clientSocket, this.request);
        BusinessService businessService = new BusinessService(this.clientSocket);

        ProductService productService = new ProductService(this.clientSocket);
        switch (this.key) {
            case LOGIN:
//                authController = new AuthController(this.clientSocket, this.request);
//                authController.login();
                break;
            case REGISTER:
//                authController = new AuthController(this.clientSocket, this.request);
//                authController.signup();
                break;
            case CREATE_BUSINESS:
                businessService.create(json_data);
                break;
            case EDIT_BUSINESS:
                businessService.update(json_data);
                break;
            case REMOVE_BUSINESS:
                businessService.removeBusiness(json_data);
            case CREATE_PRODUCT:
//                productController.registerProduct();
                break;
            case FEEDBACK:
//                FeedbackController fController = new FeedbackController(this.clientSocket, this.request);
//                fController.sendDataInDb();

                break;
            case GET_ALL_PRODUCTS:
//                productController.getAllProducts();
                break;
            case DELETE_PRODUCT:
//                productController.deleteProduct();
                break;

            case GET_PRODUCT_BY_ID:
                productService.getProductById(json_data);
                break;

            case UPDATE_PRODUCT:
                productService.updateProduct(json_data);
                break;
            case CREATE_CUSTOMER:
            customer.create();
                break;
            case GET_ALL_BUSINESSES:
                businessService.getAll();
                break;
            case GET_BUSINESS:
                businessService.getBusinessById(json_data);
                break;
            case DISABLE_CUSTOMER:
                customer.disable();
                break;

            default:
                System.out.println("\t\t\tSORRY INVALID API KEY");
        }
    }

}
