package com.customify.cli.data_format;
import com.customify.cli.Keys;

public class GetEmployee {

    private Keys key;
    private int employeeId;
    public GetEmployee() {}

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public GetEmployee(Keys key, int employeeId) {
        this.key = Keys.GET_EMPLOYEE;
        this.employeeId = employeeId;
    }
}

