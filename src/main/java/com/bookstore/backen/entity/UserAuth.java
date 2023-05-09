package com.bookstore.backen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@Table(name = "user_auth", schema = "bookstore")
public class UserAuth {
    @Id
    @Column(name = "auth_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "user_type")
    private int userType;
     // 0代表正常状态，1代表被禁止登陆了
    @Basic
    @Column(name = "forbidden_status")
    private Integer forbiddenStatus;
    @OneToOne(mappedBy = "userAuth", fetch = FetchType.LAZY)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getForbiddenStatus() {
        return forbiddenStatus;
    }

    public void setForbiddenStatus(Integer forbiddenStatus) {
        this.forbiddenStatus = forbiddenStatus;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }


}
