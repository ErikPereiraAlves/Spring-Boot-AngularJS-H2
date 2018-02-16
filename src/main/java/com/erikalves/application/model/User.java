package com.erikalves.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="USER")
public class User implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "USER_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @NotNull
    @Column (name = "USER_PASSWORD")
    private String userPassword;

    @NotNull
    @Column (name = "USER_EMAIL")
    private String userEmail;


    @NotNull
    @Column (name = "USER_ROLES")
    private String[] userRoles;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column (name = "PRODUCT_CREATED_TS")
    private Date productCreatedTs;


    @JsonFormat(pattern="yyyy-MM-dd")
    @Column (name = "PRODUCT_UPDATED_TS")
    private Date productUpdatedTs;


    public User() {
    }

    public User(String userName, String userPassword, String userEmail, String[] userRoles, Date productCreatedTs, Date productUpdatedTs) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userRoles = userRoles;
        this.productCreatedTs = productCreatedTs;
        this.productUpdatedTs = productUpdatedTs;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String[] userRoles) {
        this.userRoles = userRoles;
    }

    public Date getProductCreatedTs() {
        return productCreatedTs;
    }

    public void setProductCreatedTs(Date productCreatedTs) {
        this.productCreatedTs = productCreatedTs;
    }

    public Date getProductUpdatedTs() {
        return productUpdatedTs;
    }

    public void setProductUpdatedTs(Date productUpdatedTs) {
        this.productUpdatedTs = productUpdatedTs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + userId + '\'' +
                "name='" + userName + '\'' +
                "name='" + userName + '\'' +
                "password='" + userPassword + '\'' +
                "roles='" + userRoles + '\'' +
                "productCreatedTs='" + productCreatedTs + '\'' +
                "productUpdatedTs='" + productUpdatedTs + '\'' +

                '}';
    }
}
