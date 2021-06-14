package com.customify.cli.data_format.billing;

import com.customify.cli.Keys;
import com.customify.server.models.billing.PlanModel;

// Created BY Moss Aimelyse whole document

public class PlanFormatClient extends PlanModel<Integer, String, String,Integer> {
    private Keys key;

    public PlanFormatClient(Keys key, int planId, String planTitle, String planDescription,int price) {
        super(planId, planTitle, planDescription,price);
        this.key =key;
    }
    public void setKey(Keys key) {
        this.key = key;
    }
    public Keys getKey() {
        return this.key;
    }
}
