package com.customify.desktop.data_formats.employee;

import com.customify.desktop.Keys;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeeDataFormat {

    private int empId;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private int businessId;
    private String password;
    private String createdAt;
    public Keys key;

    public EmployeeDataFormat(String firstName, String lastName, String email, String title, int businessId ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.businessId = businessId;
        this.empId = this.generateId();
        this.createdAt = LocalDate.now().toString();
    }

    public EmployeeDataFormat(String firstName, String lastName, String email, String title,String password, int businessId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.businessId = businessId;
        this.password = password;

        this.empId = this.generateId();
        this.createdAt = LocalDate.now().toString();
    }

    public EmployeeDataFormat(int empId, String firstName, String lastName, String email, String title, int businessId, String createdAt) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.businessId = businessId;
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDataFormat that = (EmployeeDataFormat) o;
        return empId == that.empId && email.equals(that.email) && Objects.equals(title, that.title) && Objects.equals(businessId, that.businessId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, email, title, businessId);
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Keys getKey() {
        return key;
    }

    public void setKey(Keys key) {
        this.key = key;
    }

    public int generateId(){
        int max = 99999999;
        int min=100000;

        return (int) (Math.random() * (max - min + 1) + min);
    }
}
