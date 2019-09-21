package com.example.studentlist;

import java.io.Serializable;

public class Model implements Serializable {

    int id;
    String name;
    String email;
    String address;
    String course;
    String contact;
    int totalFee;
    int feePaid;

    public Model(String name, String address, String email, String course, String contact, int totalFee, int feePaid) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.course = course;
        this.contact = contact;
        this.totalFee = totalFee;
        this.feePaid = feePaid;
    }

    public Model(int id, String name, String email, String address, String course, String contact, int totalFee, int feePaid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.course = course;
        this.contact = contact;
        this.totalFee = totalFee;
        this.feePaid = feePaid;
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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCourse() { return course; }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(int feePaid) {
        this.feePaid = feePaid;
    }

    @Override
    public String toString() {
        return name;
    }
}
