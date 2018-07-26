package com.example.diu.diulabsolution.Model;

public class Room {

    private String roomId;
    private String roomName;
    private int roomImageId=NO_IMAGE_PROVIED;
    private static final int NO_IMAGE_PROVIED=-1;


    public Room(String roomId,String roomName,int roomImageId){
        this.roomId=roomId;
        this.roomName=roomName;
        this.roomImageId=roomImageId;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomImageId() {
        return roomImageId;
    }

    //check has image
    public boolean hasImag(){
        return roomImageId!=NO_IMAGE_PROVIED;
    }

}
