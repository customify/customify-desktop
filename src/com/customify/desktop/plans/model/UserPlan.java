package com.customify.desktop.plans.model;

public class UserPlan {
    private int id;
    private int customer_id;
    private int plan_id;
    public UserPlan(){}

    public UserPlan(int id, int customer_id, int plan_id) {
        this.id = id;
        this.customer_id = customer_id;
        this.plan_id = plan_id;
    }

    public UserPlan(int customer_id, int plan_id) {
        this.customer_id = customer_id;
        this.plan_id = plan_id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return this.customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getPlan_id() {
        return this.plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }
}
