package com.example.demo;

import java.util.Date;

public class Book {

    private int ID,num_of_pages;
    private String title , language , category , author , publisher;
    private Date publish_date;

    private Double price;
    private boolean available;
    public Book(int iD, String title, String language, String category, String author,
                String publisher, Date publish_date, Double price, int num_of_pages, boolean available) {
        ID = iD;
        this.num_of_pages = num_of_pages;
        this.title = title;
        this.language = language;
        this.category = category;
        this.author = author;
        this.publisher = publisher;
        this.publish_date = publish_date;
        this.price = price;
        this.available = available;
    }

    public Date getPublish_date() {
        return publish_date;
    }
    public void setPublish_date(Date publish_date) {
        this.publish_date = publish_date;
    }


    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public int getNum_of_pages() {
        return num_of_pages;
    }
    public void setNum_of_pages(int num_of_pages) {
        this.num_of_pages = num_of_pages;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

}
