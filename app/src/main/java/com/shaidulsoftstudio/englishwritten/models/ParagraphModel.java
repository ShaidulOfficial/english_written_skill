package com.shaidulsoftstudio.englishwritten.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParagraphModel {

    private String paraname;
    private String pdescription;

    private String paratitle;
    private String paradetails;

    private String storyname;
    private String storydetails;

    long timestamp;
    boolean isfavourite;

    public ParagraphModel() {
    }

    public ParagraphModel(String paraname, String pdescription, String paratitle, String paradetails, String storyname, String storydetails, long timestamp, boolean isfavourite) {
        this.paraname = paraname;
        this.pdescription = pdescription;
        this.paratitle = paratitle;
        this.paradetails = paradetails;
        this.storyname = storyname;
        this.storydetails = storydetails;
        this.timestamp = timestamp;
        this.isfavourite = isfavourite;
    }

    public String getParaname() {
        return paraname;
    }

    public void setParaname(String paraname) {
        this.paraname = paraname;
    }

    public String getPdescription() {
        return pdescription;
    }

    public void setPdescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public String getParatitle() {
        return paratitle;
    }

    public void setParatitle(String paratitle) {
        this.paratitle = paratitle;
    }

    public String getParadetails() {
        return paradetails;
    }

    public void setParadetails(String paradetails) {
        this.paradetails = paradetails;
    }

    public String getStoryname() {
        return storyname;
    }

    public void setStoryname(String storyname) {
        this.storyname = storyname;
    }

    public String getStorydetails() {
        return storydetails;
    }

    public void setStorydetails(String storydetails) {
        this.storydetails = storydetails;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isIsfavourite() {
        return isfavourite;
    }

    public void setIsfavourite(boolean isfavourite) {
        this.isfavourite = isfavourite;
    }
}
