package com.example.demo;

import java.util.Date;

public class Member {
    private int ID;
    private String fname,lname,gender,email,password,address,fav_books;
    private Date birth_date,expiry_date;
    public Member(int iD, String fname, String lname, String gender, String email, String password, String address,
                  Date birth_date, String fav_books, Date expiry_date) {
        ID = iD;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.address = address;
        this.fav_books = fav_books;
        this.birth_date = birth_date;
        this.expiry_date = expiry_date;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getFav_books() {
        return fav_books;
    }
    public void setFav_books(String fav_books) {
        this.fav_books = fav_books;
    }
    public Date getBirth_date() {
        return birth_date;
    }
    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }
    public Date getExpiry_date() {
        return expiry_date;
    }
    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

}
