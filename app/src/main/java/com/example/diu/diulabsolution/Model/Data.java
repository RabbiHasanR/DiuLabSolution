package com.example.diu.diulabsolution.Model;

public class Data {

    public static Data data =null;

    public static Data shared(){
        if (data==null){
            data = new Data();
        }
        return data;
    }

    public static String complainDocId;
    public static String notifyDocId;
    public static String getComplainDocId() {
        return complainDocId;
    }

    public static void setComplainDocId(String complainDocId) {
        Data.complainDocId = complainDocId;
    }

    public static String getNotifyDocId() {
        return notifyDocId;
    }

    public static void setNotifyDocId(String notifyDocId) {
        Data.notifyDocId = notifyDocId;
    }
}

