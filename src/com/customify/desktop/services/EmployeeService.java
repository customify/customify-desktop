package com.customify.desktop.services;

import com.customify.desktop.data_formats.employee.EmployeeDataFormat;

import java.net.Socket;

public class EmployeeService {
    public final Socket socket;

    public EmployeeService(Socket socket) {
        this.socket = socket;
    }

    public void updateEmployee(EmployeeDataFormat format){

    }
}
