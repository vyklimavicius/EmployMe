package com.klimavicius.models;



public class Auth {

    private String email, password;

    
    // default constructor
    public Auth(){
        super();
    }


    public Auth(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Auth [email=" + email + ", password=" + password + "]";
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

    
}