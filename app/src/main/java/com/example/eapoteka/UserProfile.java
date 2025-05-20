package com.example.eapoteka;

public class UserProfile {
    private static UserProfile instance;
    private String username;
    private String email;
    private String password;

    private UserProfile() {
    }

    public static UserProfile getInstance() {
        if (instance == null) {
            instance = new UserProfile();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void clearUserData() {
        username = null;
        email = null;
        password = null;
    }
}
