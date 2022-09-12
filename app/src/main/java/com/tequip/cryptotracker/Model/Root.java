package com.tequip.cryptotracker.Model;

public class Root {
    public Data data;
    public Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}


