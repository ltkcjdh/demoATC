package com.example.demo21;

public class usersATS {

    int user_id;
    String User_name, Login_user, Pass_user, Email_user, Pass2_user;

    public usersATS(int userId, String userName, String loginUser, String passUser, String emailUser, String pass2User) {
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }
    public void setLogin_user(String Login_user) {
        this.Login_user = Login_user;
    }
    public void setPass_user(String Pass_user) {
        this.Pass_user = Pass_user;
    }
    public void setEmail_user(String Email_user) {
        this.Email_user = Email_user;
    }
    public void setPass2_user(String Pass2_user) {
        this.Pass2_user = Pass2_user;
    }

    public int getUser_id() {
        return user_id;
    }
    public String getUser_name() {
        return User_name;
    }
    public String getLogin_user() {
        return Login_user;
    }
    public String getPass_user() {
        return Pass_user;
    }
    public String getEmail_user() {
        return Email_user;
    }
    public String getPass2_user() {
        return Pass2_user;
    }


    public usersATS(int user_id, String User_name, String Login_user, String Pass_user, String Email_user, String Pass2_user, String Dolgnost_user ) {
        this.user_id = user_id;
        this.User_name = User_name;
        this.Login_user = Login_user;
        this.Pass_user = Pass_user;
        this.Email_user = Email_user;
        this.Pass2_user = Pass2_user;

    }
}


