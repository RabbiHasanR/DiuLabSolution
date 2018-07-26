package com.example.diu.diulabsolution.Model;

public class Complain {
    private String complainId;
    private String computerId;
    private String complainType;
    private String complainDescription;
    private long date;

    public Complain( String computerId, String complainType, String complainDescription,long date) {
        this.computerId = computerId;
        this.complainType = complainType;
        this.complainDescription = complainDescription;
        this.date=date;
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
}
