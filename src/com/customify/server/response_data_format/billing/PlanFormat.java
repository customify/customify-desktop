package com.customify.server.response_data_format.billing;

/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

import com.customify.server.models.billing.PlanModel;

public class PlanFormat extends PlanModel<Integer, String, String,Integer> {
    public PlanFormat(){}
    public PlanFormat(int planId, String planTitle, String planDescription, int price) {
        super(planId, planTitle, planDescription, price);
    }
}
