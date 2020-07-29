package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {

    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer userid;


    public Note(Integer noteId, String notetitle, String notedescription) {
        this.noteid = noteId;
        this.notetitle = notetitle;
        this.notedescription = notedescription;
    }

    public Integer getNoteid() {
        return noteid;
    }

    public void setNoteid(Integer noteId) {
        this.noteid = noteId;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotedescription() {
        return notedescription;
    }

    public void setNotedescription(String notedescription) {
        this.notedescription = notedescription;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
