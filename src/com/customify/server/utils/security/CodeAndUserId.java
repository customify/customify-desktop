package com.customify.server.utils.security;

import java.util.Date;
import java.util.Random;

/**
 * @author SAMUEL DUSHIMIMANA
 * @role
 * This  Class generates the IDS , CODE according the the current TIME _ STAMP ...
 * * */

public class CodeAndUserId {

    /**
     * @author SAMUEL DUSHIMIMANA
     * @role
     * This  method will generate the Customer ID according to  the current TIME -= STAMP DATE
     * * */


    public String generateCustomerId(){
        String dateStub = String.format("%tM%<tS%<tL", new Date());
        dateStub = dateStub.substring(0,7);
        return dateStub;
    }

    /**
     * @author SAMUEL DUSHIMIMANA
     * @role
     * This  method will generate the Employee ID according to  the current TIME -= STAMP DATE
     * * */

    public int generateEmpId(){
        String dateStub = String.format("%tM%<tS%<tL", new Date());
        dateStub = dateStub.substring(0,10);
        int id = Integer.parseInt(dateStub);
        return id;
    }

    /**
     * @author SAMUEL DUSHIMIMANA
     * @role
     * This  method will generate the Customer Code ...
     * * */


    public String generateCustomerCode(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }


        String generatedString = buffer.toString();
        String dateStub = String.format("%tM%<tS%<tL", new Date());

        dateStub = dateStub.substring(0,3);

        return generatedString.toUpperCase()+"-"+dateStub;
    }


}
