package com.example.sweathouse.security.registration;

public class RegistrationRequest {

    private String username;
    private String password;

    public RegistrationRequest() {

    }

    public RegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "email='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
