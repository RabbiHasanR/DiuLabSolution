package com.example.diu.diulabsolution.Model;

public class Notification {

   private String notificationTitle;
   private String notificationSenderId;
   private String complainId;
   private String complainType;

   public Notification(){

   }

    public Notification(String notificationTitle, String notificationSenderId,String complainId,String complainType) {
        this.notificationTitle = notificationTitle;
        this.notificationSenderId = notificationSenderId;
        this.complainId=complainId;
        this.complainType=complainType;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationSenderId() {
        return notificationSenderId;
    }

    public void setNotificationSenderId(String notificationSenderId) {
        this.notificationSenderId = notificationSenderId;
    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }
}
