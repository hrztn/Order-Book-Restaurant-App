package com.example.makanat.model;

//manage, save, retrieve from firebase
public class UserModelClass {
    String userName;
    String userEmail;
    String userPhoneNo;
    String userAddress;
    String userId;
    String userImage;

    public UserModelClass(){

    }

    public UserModelClass(String userName, String userEmail, String userPhoneNo, String userAddress, String userId, String userImage) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNo = userPhoneNo;
        this.userAddress = userAddress;
        this.userId = userId;
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public void setUserPhoneNo(String userPhoneNo) {
        this.userPhoneNo = userPhoneNo;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
