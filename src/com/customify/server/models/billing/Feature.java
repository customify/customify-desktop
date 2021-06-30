package com.customify.server.models.billing;

import java.io.Serializable;
public class Feature implements Serializable{
    private static final long serialVersionUID = 2018040801L;
    private int id;
    private String name;
    private String description;
    public Feature() {
        super();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}