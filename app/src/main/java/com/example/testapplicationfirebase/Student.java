package com.example.testapplicationfirebase;

public class Student {
    private String Id;
    private String Name;
    private String Address;
    private int ContactNo;

    public Student() {
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public int getContactNo() {
        return ContactNo;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setContactNo(int contactNo) {
        ContactNo = contactNo;
    }
}
