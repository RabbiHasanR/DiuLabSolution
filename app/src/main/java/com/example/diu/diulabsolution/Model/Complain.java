package com.example.diu.diulabsolution.Model;

public class Complain {
    private String complainId;
    private String computerId;
    private String complainType;
    private String complainDescription;
    private String fromUserId;


    public Complain( String computerId, String complainType, String complainDescription) {
        this.computerId = computerId;
        this.complainType = complainType;
        this.complainDescription = complainDescription;

    }

    public String getComplainId() {
        return complainId;
    }

    public void setComplainId(String complainId) {
        this.complainId = complainId;
    }

    public String getComputerId() {
        return computerId;
    }

    public void setComputerId(String computerId) {
        this.computerId = computerId;
    }

    public String getComplainType() {
        return complainType;
    }

    public void setComplainType(String complainType) {
        this.complainType = complainType;
    }

    public String getComplainDescription() {
        return complainDescription;
    }

    public void setComplainDescription(String complainDescription) {
        this.complainDescription = complainDescription;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }
}
