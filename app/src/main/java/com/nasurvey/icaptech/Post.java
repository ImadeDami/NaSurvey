package com.nasurvey.icaptech;

public class Post {

    private String firstName;

    private String secondName;

    //@SerializedName("gender")
    private String gender;

    private int Sync_status;

    public Post(String firstName, String secondName, String gender, int Sync_status) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.gender = gender;
        this.Sync_status = Sync_status;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getGender() {
        return gender;
    }

    public int getSync_status(){
        return Sync_status;
    }
}
