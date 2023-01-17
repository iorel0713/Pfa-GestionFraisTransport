package com.example.gestionfraimobileandroid.beans;

public class ResponseDebloqueruser {
    private boolean success;
    private Data data ;

    public ResponseDebloqueruser(boolean success, Data data) {
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
        return "ResponseDebloqueruser{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
