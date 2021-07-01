package com.customify.desktop.data_formats.user;

public class UpdateUserFormat {

    private String userType;
    private String firName;
    private String lasName;
    private String email;
    private String id;

    public UpdateUserFormat() {
    }

    public UpdateUserFormat(String id,String userType, String firName, String lasName, String email) {
        this.userType = userType;
        this.firName = firName;
        this.lasName = lasName;
        this.email = email;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirName() {
        return firName;
    }

    public void setFirName(String firName) {
        this.firName = firName;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
