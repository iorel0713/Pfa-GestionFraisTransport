package com.example.gestionfraimobileandroid.beans;

import java.util.List;

public class Data {
    public String message;
    public String accessToken;
    public List<User> users ;
    public List<Request>requests;
    public Data(String message, String accessToken) {
        this.message = message;
        this.accessToken = accessToken;
    }

    public Data(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Data{" +
                "message='" + message + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", users=" + users +
                ", requests=" + requests +
                '}';
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public Data(String message, String accessToken, List<Request> requests) {
        this.message = message;
        this.accessToken = accessToken;
        this.requests = requests;
    }


    public Data(String message, List<User> users) {
        this.message = message;
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


}
