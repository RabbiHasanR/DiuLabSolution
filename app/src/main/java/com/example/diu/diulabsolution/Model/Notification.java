package com.example.diu.diulabsolution.Model;

public class Notification {

   private String notification_title;
   private String notification_sender_id;
   private String complain_id;
   private String complain_type;

   public Notification(){

   }

    public Notification(String notification_title, String notification_sender_id, String complain_id, String complain_type) {
        this.notification_title = notification_title;
        this.notification_sender_id = notification_sender_id;
        this.complain_id = complain_id;
        this.complain_type = complain_type;
    }

    public String getNotification_title() {
        return notification_title;
    }

    public void setNotification_title(String notification_title) {
        this.notification_title = notification_title;
    }

    public String getNotification_sender_id() {
        return notification_sender_id;
    }

    public void setNotification_sender_id(String notification_sender_id) {
        this.notification_sender_id = notification_sender_id;
    }

    public String getComplain_id() {
        return complain_id;
    }

    public void setComplain_id(String complain_id) {
        this.complain_id = complain_id;
    }

    public String getComplain_type() {
        return complain_type;
    }

    public void setComplain_type(String complain_type) {
        this.complain_type = complain_type;
    }
}
