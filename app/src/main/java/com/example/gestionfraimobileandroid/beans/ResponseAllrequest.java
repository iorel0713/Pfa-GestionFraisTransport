package com.example.gestionfraimobileandroid.beans;

public class ResponseAllrequest {
    private Boolean success;
    private Data data ;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public ResponseAllrequest(Boolean success, Data data) {
        this.success = success;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseAllrequest{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
