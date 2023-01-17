package com.example.gestionfraimobileandroid.beans;

public class ResponseRefusedrequest {
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

    public ResponseRefusedrequest(Boolean success, Data data) {
        this.success = success;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseRefusedrequest{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
