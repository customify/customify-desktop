package com.customify.server.models.billing;
/**
 * @author Mfuranziza Sekata Aimelyse Moss
 * Created and Wrote Whole Document By Moss
 * */

public class PlanModel<I,T,D,P>{
    //    S represents data type for title, description
    //    I represents data type for Id
    private I planId;
    private T planTitle;
    private D planDescription;
    private P price;
    public PlanModel(){ }
    public PlanModel(I planId, T planTitle, D planDescription, P price) {
        this.planId = planId;
        this.planTitle = planTitle;
        this.planDescription = planDescription;
        this.price = price;
    }
    public PlanModel(T planTitle, D planDescription, P price) {
        this.planTitle = planTitle;
        this.planDescription = planDescription;
        this.price = price;
    }

    public I getPlanId() {
        return this.planId;
    }

    public void setPlanId(I planId) {
        this.planId = planId;
    }

    public T getPlanTitle() {
        return this.planTitle;
    }

    public void setPlanTitle(T planTitle) {
        this.planTitle = planTitle;
    }

    public D getPlanDescription() {
        return this.planDescription;
    }

    public void setPlanDescription(D planDescription) {
        this.planDescription = planDescription;
    }

    public P getPrice() {
        return this.price;
    }

    public void setPrice(P price) {
        this.price = price;
    }
}
