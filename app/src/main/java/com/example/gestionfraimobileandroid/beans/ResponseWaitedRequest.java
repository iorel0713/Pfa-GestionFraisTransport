package com.example.gestionfraimobileandroid.beans;

public class ResponseWaitedRequest {
    private Boolean success;
    private Data data ;

    public ResponseWaitedRequest(Boolean success, Data data) {
        this.success = success;
        this.data = data;
    }

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

    @Override
    public String toString() {
        return "ResponseWaitedRequest{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
