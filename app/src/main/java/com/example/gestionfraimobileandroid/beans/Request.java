package com.example.gestionfraimobileandroid.beans;

import java.util.Date;

public class Request {
    private int id;
    private String reason;
    private String transport;
    private Date start_date;
    private Date end_date;
    private String destination_city;
    private float  total_fees;
    private String  status;
    private int userId;

    public Request(String reason, String transport, Date start_date, Date end_date, String destination_city, float total_fees, int userId) {
        this.reason = reason;
        this.transport = transport;
        this.start_date = start_date;
        this.end_date = end_date;
        this.destination_city = destination_city;
        this.total_fees = total_fees;
        this.userId = userId;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    public float getTotal_fees() {
        return total_fees;
    }

    public void setTotal_fees(float total_fees) {
        this.total_fees = total_fees;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +

                ", reason='" + reason + '\'' +
                ", transport='" + transport + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", destination_city='" + destination_city + '\'' +
                ", total_fees=" + total_fees +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}

