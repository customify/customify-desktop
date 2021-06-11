package com.customify.desktop.plans.model;

import com.customify.cli.Keys;

public class Plans {
    private int planId;
    private String planTitle;
    private String planDescription;
    private int price;
    private Keys key =Keys.CREATE_PLAN;

    public Plans(){}

    public Plans(int planId, String planTitle, String planDescription, int price) {
        this.planId = planId;
        this.planTitle = planTitle;
        this.planDescription = planDescription;
        this.price = price;
    }

    public Plans(String planTitle, String planDescription, int price) {
        this.planTitle = planTitle;
        this.planDescription = planDescription;
        this.price = price;
    }

    public int getPlanId() {
        return this.planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanTitle() {
        return this.planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getPlanDescription() {
        return this.planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }
}
