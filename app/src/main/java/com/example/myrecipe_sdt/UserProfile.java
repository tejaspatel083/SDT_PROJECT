package com.example.myrecipe_sdt;

public class UserProfile {

    public String  userage;
    public String useremail;
    public String username;
    public String userphone;

    public UserProfile(){

    }

    public UserProfile(String userage, String useremail, String username, String userphone) {
        this.userage = userage;
        this.useremail = useremail;
        this.username = username;
        this.userphone = userphone;
    }


    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }


}
