package com.bookstore.backen.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    /**
     * 1代表是普通用户，0代表是管理员
     */
    @Basic
    @Column(name = "identity")
    private int identity;
    @Basic
    @Column(name = "tel")
    private String tel;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "nickname")
    private String nickname;
        /**
         * 0代表正常状态，1代表被禁止登陆了
         * */
    @Basic
    @Column(name = "forbidden_status")
    private Integer forbiddenStatus;
    @Basic
    @Column(name = "gender")
    private int gender;
    @Basic
    @Column(name="description")
    private String description;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getForbiddenStatus() {
        return forbiddenStatus;
    }

    public void setForbiddenStatus(Integer forbidenStatus) {
        this.forbiddenStatus = forbidenStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return identity == user.identity && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(tel, user.tel) && Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, identity, tel, mail);
    }
}
