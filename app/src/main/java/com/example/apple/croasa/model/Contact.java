package com.example.apple.croasa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id_user_manager")
    @Expose
    private Integer idUserManager;
    @SerializedName("id_user_tvts")
    @Expose
    private Integer idUserTvts;
    @SerializedName("id_status_call")
    @Expose
    private Integer idStatusCall;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("current_level")
    @Expose
    private Integer currentLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdUserManager() {
        return idUserManager;
    }

    public void setIdUserManager(Integer idUserManager) {
        this.idUserManager = idUserManager;
    }

    public Integer getIdUserTvts() {
        return idUserTvts;
    }

    public void setIdUserTvts(Integer idUserTvts) {
        this.idUserTvts = idUserTvts;
    }

    public Integer getIdStatusCall() {
        return idStatusCall;
    }

    public void setIdStatusCall(Integer idStatusCall) {
        this.idStatusCall = idStatusCall;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }
}
