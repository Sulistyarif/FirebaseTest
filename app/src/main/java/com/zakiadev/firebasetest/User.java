package com.zakiadev.firebasetest;

/**
 * Created by momo on 16/04/17.
 */

public class User {
    public String name;
    public String email;
    public String paswot;
    public int saldo;

    public User(){

    }

    public User(String name, String email, String paswot, int saldo){
        this.name = name;
        this.email = email;
        this.paswot = paswot;
        this.saldo = saldo;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPaswot(){
        return paswot;
    }

    public int getSaldo() {
        return saldo;
    }
}
