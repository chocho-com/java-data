package com.chocho.model;

import com.chocho.util.StringUtil;

public class User {
    int id;
    String name;
    String password;

    public User(){
    }

    public User(String name, String password){
        id = -1;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
