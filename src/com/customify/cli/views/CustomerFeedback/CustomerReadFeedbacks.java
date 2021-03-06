package com.customify.cli.views.CustomerFeedback;

/*
 *@author: NSENGIYUMVA GERSHOM
 */
import com.customify.cli.Colors;
import com.customify.cli.services.CustomerFeedbackService;
import com.customify.cli.Keys;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.net.Socket;

public class CustomerReadFeedbacks {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream objectInput;
    private String json_data;

    public CustomerReadFeedbacks(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket() {

    }

    /*
     * Here is the function to get all the recorded feedbacks
     */

    public void GetFeedbacks() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the business id:");
        int businessId = scan.nextInt();
        String json = "{ \"businessId\" : \""+businessId+"\", \"key\" : \""+ Keys.GET_ALL_FEEDBACKS +"\" }";

        CustomerFeedbackService feedService = new CustomerFeedbackService(socket);
        feedService.getAllCustomerFeedbacks(json);
    }
    // -------------------------------------------------------------------------------

    /*
     * The function for deleting certain customer feedback
     */
    public void deleteCustomerFeedback() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.print(Colors.ANSI_GREEN);
        System.out.print("Enter feedback id: \t");
        System.out.print(Colors.ANSI_RESET);
        int feedback_id = scan.nextInt();
        String json = "{ \"feedbackId\" : \""+feedback_id+"\", \"key\" : \""+ Keys.REMOVE_FEEDBACK +"\" }";
        CustomerFeedbackService feedbackService = new CustomerFeedbackService(socket);
        feedbackService.deleteCustomerFeedback(json);
        // -------------------------------------------------------------------------------u
    }
}
