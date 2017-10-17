package com.example.prosia.listviewvolley;

/**
 * Created by PROSIA on 14/10/2017.
 */

public class ModelUser {

    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;

    public String getId(){
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
