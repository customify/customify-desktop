package com.customify.desktop.plans.model;

public class Billing {
    private int billingId;
    private int planId;
    private int featureId;
    public Billing(){}
    public Billing(int billingId, int planId, int featureId) {
        this.billingId = billingId;
        this.planId = planId;
        this.featureId = featureId;
    }

    public Billing(int planId, int featureId) {
        this.planId = planId;
        this.featureId = featureId;
    }

    public int getBillingId() {
        return this.billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public int getPlanId() {
        return this.planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getFeatureId() {
        return this.featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }
}
