package com.example.gestionfraimobileandroid.beans;

public class ResponseAcceptedrequest {
    private boolean success;
    private Data data ;

    public ResponseAcceptedrequest(boolean success, Data data) {
        this.success = success;
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
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
        return "ResponseAcceptedrequest{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
