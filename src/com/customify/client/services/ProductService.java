package com.customify.client.services;

import com.customify.client.Common;
import com.customify.client.SendToServer;
import com.customify.server.models.ProductModel;
import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;
//import com.customify.shared.requests_data_formats.ProductFormat;
import com.customify.client.data_format.products.ProductFormat;
import com.customify.shared.responses_data_format.AuthFromats.SuccessLoginFormat;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

public class ProductService {
    private Socket socket;
    private String data;
    InputStream inputStream;
    ObjectInputStream objectInputStream;

    public ProductService(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) throws IOException {
        this.socket = socket;

    }

    public void addNewProduct(ProductFormat productModel) throws Exception {
        Request request = new Request(Keys.CREATE_PRODUCT, productModel);
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer()) {
            this.handleRegisterProductSuccess();
        } else {
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }

    /**
     * @description
     * Service to Retrieve Product By Id
     * @author SAUVE Jean-Luc
     * @version 1
     * */
    public void getProductById(Integer productId) throws  Exception{
        Request request = new Request(Keys.GET_PRODUT_BY_ID,productId);
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer()) {
            this.handleGetProductByIdSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }

    }

    /**
     * @description
     * Service to Delete  Product By Product Code
     * @author Tamara Iradukunda
     * @version 1
     *
     * @param */
    public void deleteProduct(ProductFormat product) throws  Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);
        SendToServer serverSend = new SendToServer(json, this.socket);

        //if the sending is successful call a method to handle response from server
        if (serverSend.send()) {
           // System.out.println("Products deleted successfully! ");
            this.handleDeleteProductSuccess();
        } else {
            System.out.println("Error occured when deleting products ");
        }
    }

    public void getAllProducts() throws Exception {
        Request request = new Request(Keys.GET_ALL_PRODUCTS, new ProductFormat());
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer()) {
            this.handleGetProductListSuccess();
        } else {
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }

    /**
     * @description
     * Service to Update Product By Id
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void updateProduct(ProductFormat productFormat) throws  Exception{
        Request request = new Request(Keys.UPDATE_PRODUCT,productFormat);
        Common common = new Common(request, this.socket);

        //if the sending is successful call a method to handle response from server
        if (common.sendToServer()) {
            this.handleUpdateProductSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }
    public void handleGetProductListSuccess() throws IOException, ClassNotFoundException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();
            if (response.get(0).getStatusCode() == 200) {
                List<ProductFormat> products = (List<ProductFormat>) response.get(0).getData();

                if (products.size() == 0) {
                    System.out.println("\n\nNo products registered so far.\n");
                    return;
                }

                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t\t\t\t\t\tHere is a list of products registered so far");
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

                System.out.println(String.format("%-15s %-30s %-10s %10s %20s %20s", "Code", "name", "quantity", "price", "bounded points", "Created at") + "\n");

                for (int i = 0; i < products.size(); i++) {
                    ProductFormat product = products.get(i);

                    System.out.println(String.format("%-15s %-30s %-10s %10s %20s %20s", product.getProductCode(), product.getName(), product.getQuantity(), product.getPrice(), product.getBondedPoints(), product.getCreatedAt()));
                }
                System.out.println("\n");
            } else if (response.get(0).getStatusCode() == 400) {
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            } else {
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }

        } catch (IOException e) {
            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
        }

        return;
    }

    public void handleRegisterProductSuccess() throws IOException, ClassNotFoundException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();
            ;
            if (response.get(0).getStatusCode() == 200) {
                ProductFormat registeredProduct = (ProductFormat) response.get(0).getData();

                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t product registered successfully");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            } else if (response.get(0).getStatusCode() == 400) {
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            } else {
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }

        } catch (IOException e) {
            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
        }

        return;
    }

    /**
     * @description
     * Function to Send Response when Product is Retrieved Successfully
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void handleGetProductByIdSuccess() throws IOException, ClassNotFoundException{
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();
            if(response.get(0).getStatusCode() == 200){
                ProductFormat retrievedProduct = (ProductFormat) response.get(0).getData();

                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("Product Code: " + retrievedProduct.getProductCode());
                System.out.println("Business Id: "+ retrievedProduct.getBusiness_id());
                System.out.println("Name: " + retrievedProduct.getName());
                System.out.println("Price: " + retrievedProduct.getPrice() );
                System.out.println("Quantity: " + retrievedProduct.getQuantity());
                System.out.println("Description: " + retrievedProduct.getDescription());
                System.out.println("Bonded Points: " + retrievedProduct.getBondedPoints());
                System.out.println("Registered By: " + retrievedProduct.getRegistered_by());
                System.out.println("Created At: " + retrievedProduct.getCreatedAt());
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            }
            else if(response.get(0).getStatusCode() == 400){
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            }
            else{
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }

        } catch (IOException e) {
            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
        }

        return;
    }

    /**
     * @description
     * Function to Send Response when Product is Deleted Successfully
     * @author Tamara Iradukunda
     * @version 1
     * */
    public void handleDeleteProductSuccess() throws  Exception, ClassNotFoundException {
        inputStream = this.socket.getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        ObjectMapper objectMapper=new ObjectMapper();
        System.out.println("Ready client!");
//        try {
             String data=(String)objectInputStream.readObject();
             JsonNode jsonFormat=objectMapper.readTree(data);
             //System.out.println(jsonFormat.get("StatusCode").asInt());
             int statusCode=jsonFormat.get("StatusCode").asInt();

             if(statusCode==200){
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t product deleted successfully");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
             }
             else if(statusCode==400){
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
             }
             else{
                 System.out.println("Internal server error!!");
             }

//        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
//        }
    }

    /**
     * @description
     * Function to Send Response when Product is Updated Successfully
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void handleUpdateProductSuccess() throws IOException, ClassNotFoundException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
        try {
            List<Response> response = (List<Response>) objectInputStream.readObject();
            System.out.println("Status: "+ response.get(0).getStatusCode());
            if(response.get(0).getStatusCode() == 200){
                ProductFormat registeredProduct = (ProductFormat) response.get(0).getData();

                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t product Updated successfully");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            }
            else if(response.get(0).getStatusCode() == 400){
                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
            }
            else{
                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
            }

        } catch (IOException e) {
            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
        } catch (ClassNotFoundException e) {
            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
        }

        return;
    }

}