package com.btp_iitj.cnfmanag;

public class conference_1 {
    private String name, date, venue, description;

    public conference_1() {

    }

    public conference_1(String date, String name, String venue, String description) {
        this.date = date;
        this.name = name;
        this.venue = venue;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String name) {
        this.date = name;
    }

    public void setVenue(String name) {
        this.venue = name;
    }

    public void setDescription(String name) {
        this.description = name;
    }
}
