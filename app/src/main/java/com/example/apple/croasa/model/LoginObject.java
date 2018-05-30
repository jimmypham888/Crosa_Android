package com.example.apple.croasa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginObject implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id_role")
    @Expose
    private Integer idRole;
    @SerializedName("statition_default")
    @Expose
    private String statitionDefault;
    @SerializedName("statition_extend")
    @Expose
    private String statitionExtend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getStatitionDefault() {
        return statitionDefault;
    }

    public void setStatitionDefault(String statitionDefault) {
        this.statitionDefault = statitionDefault;
    }

    public String getStatitionExtend() {
        return statitionExtend;
    }

    public void setStatitionExtend(String statitionExtend) {
        this.statitionExtend = statitionExtend;
    }
}
