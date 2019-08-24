package com.anitha.offsitefinal.model;


import android.graphics.Bitmap;

public class User {

    private int clock;
    private String name;
    private String email;
    private String password;
    private String cpassword;
    private long mob;
    private String branch;
    private String hub;
    byte[] _img;

    public User() {
    }

    // constructor
    public User( byte[] img){

        this._img = img;

    }


    public int getClock() {
        return clock;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getcPassword() {
        return cpassword;
    }

    public void setcPassword(String cpassword) {
        this.cpassword = cpassword;
    }


    public long getMob() {
        return mob;
    }

    public void setMob(long mob) {
        this.mob = mob;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getHub() {
        return hub;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }

    //getting profile pic
    public byte[] getPhoto(){
        return this._img;
    }

    //setting profile pic

    public void setPhoto(byte[] b){
        this._img=b;
    }
}
