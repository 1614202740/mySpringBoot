package com.example.demo.entity;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String state;
    private String name;
    private String gender;
    private String birth;

    public static final String STATE_ACCOUNT_EXPIRED = "1000";
    public static final String STATE_LOCK = "0100";
    public static final String STATE_TOKEN_EXPIRED = "0010";
    public static final String STATE_NORMAL = "0001";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
