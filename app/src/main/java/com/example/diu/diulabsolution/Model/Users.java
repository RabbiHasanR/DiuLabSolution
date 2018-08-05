package com.example.diu.diulabsolution.Model;

public class Users {
    private String name;
    private String user_id;
    private String user_type;
    private String email;
    private String password;
    private String images;


    public Users(){

    }

    public Users(String name, String user_id, String user_type, String email, String password, String images) {
        this.name = name;
        this.user_id = user_id;
        this.user_type = user_type;
        this.email = email;
        this.password = password;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}
