package com.customify.client.services;

import com.customify.client.SendToServer;
<<<<<<< HEAD
import com.customify.server.models.ProductModel;
import com.customify.shared.Keys;
import com.customify.shared.Request;
import com.customify.shared.Response;
//import com.customify.shared.requests_data_formats.ProductFormat;
import com.customify.client.data_format.products.ProductFormat;
import com.customify.shared.responses_data_format.AuthFromats.SuccessLoginFormat;
import com.fasterxml.jackson.databind.JsonNode;
=======

import com.customify.client.data_format.products.ProductFormat;
>>>>>>> 00c0dc401d8d60ac3aa9e7e19a4a163278a737d0
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
//        Request request = new Request(Keys.CREATE_PRODUCT, productModel);
//        Common common = new Common(request, this.socket);
//
//        //if the sending is successful call a method to handle response from server
//        if (common.sendToServer()) {
//            this.handleRegisterProductSuccess();
//        } else {
//            System.out.println("\n\nError occurred when trying to send request to server\n");
//        }
    }

    /**
     * @description
     * Service to Retrieve Product By Id
     * @author SAUVE Jean-Luc
     * @version 1
     * */
    public void getProductById(Integer productId) throws  Exception{

        String jsonToSend = "{\"key\" : \"GET_PRODUCT_BY_ID\", \"id\" : \""+productId+"\"}";
        SendToServer sendToServer = new SendToServer(jsonToSend, this.socket);

        //if the sending is successful call a method to handle response from server
        if (sendToServer.send()) {
            System.out.println("Reached here 1");
            this.handleGetProductByIdSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }

    }
<<<<<<< HEAD

    /**
     * @description Service to Delete  Product By Product Code
     * @author Tamara Iradukunda
     * @version 1
     *
     * @param */
    public void deleteProduct(ProductFormat product) throws  Exception{
        // ObjectMapper provides functionality for reading and writing in JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonProductFormat = mapper.writeValueAsString(product);
        SendToServer serverSend = new SendToServer(jsonProductFormat, this.socket);
        if (serverSend.send()) {
           // System.out.println("Send Products to the server successfully! ");
            this.handleDeleteProductSuccess();
        } else {
            System.out.println("Error occured when deleting products ");
        }
=======
    //Method Created By Merlyne Iradukunda
    // Due date: 6/2/2020
    public void deleteProduct(Long productCode) throws  Exception{
//        Request request = new Request(Keys.DELETE_PRODUCT, productCode);
//        Common common = new Common(request, this.socket);
//
//        //if the sending is successful call a method to handle response from server
//        if (common.sendToServer()) {
//            this.handleDeleteProductSuccess();
//        } else {
//            System.out.println("\n\nError occurred when trying to send request to server\n");
//        }
>>>>>>> 00c0dc401d8d60ac3aa9e7e19a4a163278a737d0
    }

    public void getAllProducts() throws Exception {
//        Request request = new Request(Keys.GET_ALL_PRODUCTS, new ProductFormat());
//        Common common = new Common(request, this.socket);
//
//        //if the sending is successful call a method to handle response from server
//        if (common.sendToServer()) {
//            this.handleGetProductListSuccess();
//        } else {
//            System.out.println("\n\nError occurred when trying to send request to server\n");
//        }
    }

    /**
     * @description
     * Service to Update Product By Id
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void updateProduct(ProductFormat productFormat) throws  Exception{

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productFormat);
        SendToServer serverSend = new SendToServer(json, this.socket);
        //if the sending is successful call a method to handle response from server
        if (serverSend.send()) {
            this.handleUpdateProductSuccess();
        }
        else{
            System.out.println("\n\nError occurred when trying to send request to server\n");
        }
    }
    public void handleGetProductListSuccess() throws IOException, ClassNotFoundException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
//
//        try {
////            List<Response> response = (List<Response>) objectInputStream.readObject();
////            if (response.get(0).getStatusCode() == 200) {
////                List<ProductFormat> products = (List<ProductFormat>) response.get(0).getData();
////
////                if (products.size() == 0) {
////                    System.out.println("\n\nNo products registered so far.\n");
////                    return;
////                }
////
////                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
////                System.out.println("\t\t\t\t\t\t\tHere is a list of products registered so far");
////                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
////
////                System.out.println(String.format("%-15s %-30s %-10s %10s %20s %20s", "Code", "name", "quantity", "price", "bounded points", "Created at") + "\n");
////
////                for (int i = 0; i < products.size(); i++) {
////                    ProductFormat product = products.get(i);
////
////                    System.out.println(String.format("%-15s %-30s %-10s %10s %20s %20s", product.getProductCode(), product.getName(), product.getQuantity(), product.getPrice(), product.getBondedPoints(), product.getCreatedAt()));
////                }
////                System.out.println("\n");
////            } else if (response.get(0).getStatusCode() == 400) {
////                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
////            } else {
////                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
////            }
//
//        } catch (IOException e) {
//            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
//        } catch (ClassNotFoundException e) {
//            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
//        }

        return;
    }

    public void handleRegisterProductSuccess() throws IOException, ClassNotFoundException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);

//        try {
//            List<Response> response = (List<Response>) objectInputStream.readObject();
//            ;
//            if (response.get(0).getStatusCode() == 200) {
//                ProductFormat registeredProduct = (ProductFormat) response.get(0).getData();
//
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println("\t\t product registered successfully");
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//            } else if (response.get(0).getStatusCode() == 400) {
//                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
//            } else {
//                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
//            }
//
//        } catch (IOException e) {
//            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
//        } catch (ClassNotFoundException e) {
//        }//            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");

        return;
    }

    /**
     * @description
     * Function to Send Response when Product is Retrieved Successfully
     * @author SAUVE Jean-Luc
     * @version 1
     * */

    public void handleGetProductByIdSuccess() throws IOException, ClassNotFoundException{
        System.out.println("Reached here 2");
        inputStream = this.getSocket().getInputStream();
        System.out.println("Reached here 3");
        objectInputStream = new ObjectInputStream(inputStream);
        System.out.println("Reached here 4");
//        try {
//            List<Response> response = (List<Response>) objectInputStream.readObject();
//            if(response.get(0).getStatusCode() == 200){
//                ProductFormat retrievedProduct = (ProductFormat) response.get(0).getData();
//
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println("Product Code: " + retrievedProduct.getProductCode());
//                System.out.println("Business Id: "+ retrievedProduct.getBusiness_id());
//                System.out.println("Name: " + retrievedProduct.getName());
//                System.out.println("Price: " + retrievedProduct.getPrice() );
//                System.out.println("Quantity: " + retrievedProduct.getQuantity());
//                System.out.println("Description: " + retrievedProduct.getDescription());
//                System.out.println("Bonded Points: " + retrievedProduct.getBondedPoints());
//                System.out.println("Registered By: " + retrievedProduct.getRegistered_by());
//                System.out.println("Created At: " + retrievedProduct.getCreatedAt());
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//            }
//            else if(response.get(0).getStatusCode() == 400){
//                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
//            }
//            else{
//                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
//            }

//        } catch (IOException e) {
//            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
//        } catch (ClassNotFoundException e) {
//            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
//        }

        return;
    }
<<<<<<< HEAD

    /**
     * @description
     * Function to Send Response when Product is Deleted Successfully
     * @author Tamara Iradukunda
     * @version 1
     * */
    public void handleDeleteProductSuccess() throws  Exception,ClassNotFoundException {
        try {
            inputStream = this.socket.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            ObjectMapper objectMapper=new ObjectMapper();

            String data = (String) objectInputStream.readObject();
            //System.out.println("+++++++++++++\n" +" data got from the server  is\n" +"=>"+data+"+++++\n");
            JsonNode jsonFormat = objectMapper.readTree(data);
            int statusCode = jsonFormat.get("StatusCode").asInt();
           // System.out.println(statusCode);

            if (statusCode == 200) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\t\t product deleted successfully");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++\n\n");
            }
            // product test code 6503709,47462944,57191349,80316413
            else{
                System.out.println("\nInvalid product Code!\n");
            }
        } catch (Exception e) {
            System.out.println("\n\nError occurred :" + e.getMessage() + "\n\n");
        }
=======
    public void handleDeleteProductSuccess() throws  Exception, ClassNotFoundException {
        inputStream = this.getSocket().getInputStream();
        objectInputStream = new ObjectInputStream(inputStream);
//        try {
//            List<Response> response = (List<Response>) objectInputStream.readObject();
//
//            if (response.get(0).getStatusCode() == 200) {
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println("\t\t product deleted successfully");
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//            } else if (response.get(0).getStatusCode() == 400) {
//                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
//            } else if(response.get(0).getStatusCode() == 500){
//                System.out.println("Internal server error!!");
//            }else{
//                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
//            }
//        } catch (IOException e) {
//            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
//        } catch (ClassNotFoundException e) {
//            System.out.println("\n\nError occurred:" + e.getMessage() + "\n\n");
//        }

        return;
>>>>>>> 00c0dc401d8d60ac3aa9e7e19a4a163278a737d0
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
//        try {
//            List<Response> response = (List<Response>) objectInputStream.readObject();
//            System.out.println("Status: "+ response.get(0).getStatusCode());
//            if(response.get(0).getStatusCode() == 200){
//                ProductFormat registeredProduct = (ProductFormat) response.get(0).getData();
//
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//                System.out.println("\t\t product Updated successfully");
//                System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
//            }
//            else if(response.get(0).getStatusCode() == 400){
//                System.out.println("\n\nInvalid product format.Please enter product details as required\n\n");
//            }
//            else{
//                System.out.println("\n\nUnknown error occurred.Check your internet connection\n");
//            }

//        } catch (IOException e) {
//            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
//        } catch (ClassNotFoundException e) {
//            System.out.println("\n\nError occurred:" +e.getMessage()+ "\n\n");
//        }

        return;
    }

}