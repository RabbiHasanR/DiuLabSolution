package com.example.diu.diulabsolution.Model;

public class Users {
    private String userName;
    private String userId;
    private String userType;
    private String userEmail;
    private String password;
    private String userImage;


    public Users(){

    }
   public Users(String userImage,String userName,String userId,String userType,String userEmail,String password){
        this.userImage=userImage;
        this.userName=userName;
        this.userId=userId;
        this.userType=userType;
        this.userEmail=userEmail;
        this.password=password;
   }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
