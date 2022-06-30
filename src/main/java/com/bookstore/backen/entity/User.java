package com.bookstore.backen.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * User 实体类
 * */

@Entity
@Table(name ="user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;      //用户编号
    private String username;     //用户名
    private String password;     //用户密码
    private Integer identity;
    private Integer gender;
    private String tel;
    private String mail;

    public User() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public Integer getGender() {
        return gender;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public String getUsername() {
        return username;
    }

    public Integer getIdentity() {
        return identity;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUser_type(Integer user_type) {
        return this.identity;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdentity(Integer user_type) {
        this.identity = user_type;
    }


}


