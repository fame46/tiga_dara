package com.example.myapplication.Model;

public class MejaModel {
    int id, meja;
    String created_on, created_by, modified_on, modified_by;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeja() {
        return meja;
    }

    public void setMeja(int meja) {
        this.meja = meja;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getModified_on() {
        return modified_on;
    }

    public void setModified_on(String modified_on) {
        this.modified_on = modified_on;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }
}
