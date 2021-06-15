package com.customify.server.response_data_format.employee;

public class CreateFormat {
    String message;
    String json_data;
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJson_data() {
        return json_data;
    }

    public void setJson_data(String json_data) {
        this.json_data = json_data;
        this.status = status;
    }

    public CreateFormat() {}
    public CreateFormat(String message,int status) {
        this.message = message;
        this.status = status;
    }
}
