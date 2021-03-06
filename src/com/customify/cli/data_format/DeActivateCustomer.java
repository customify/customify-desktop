package com.customify.cli.data_format;

import com.customify.cli.Keys;

/**
 * @author Murenzi Confiance Tracy
 * @role
 * this is the the format for the deactivating the card on the client side
 * */
public class DeActivateCustomer {
    private Keys key;
    private String code;
    public DeActivateCustomer(String code) {
        this.code = code;
        this.key = Keys.RENABLE_CUSTOMER;
    }
    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}