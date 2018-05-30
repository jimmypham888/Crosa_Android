package com.example.apple.croasa.model;

public class Contacts {
    private String imageResource;
    private String Name;
    private String Email;
    private String Phone;
    private String rank;

    public Contacts(String imageResource, String name, String email, String phone, String rank) {
        this.imageResource = imageResource;
        Name = name;
        Email = email;
        Phone = phone;
        this.rank = rank;
    }

    public Contacts(String name, String email, String phone, String rank) {
        Name = name;
        Email = email;
        Phone = phone;
        this.rank = rank;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
