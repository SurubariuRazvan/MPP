package com.jderu;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "UserDB")
public class User implements com.jderu.Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password_hash")
    private String passwordHash;

    public User(Integer id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public User() {
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public static String hash(String password) {
        return DigestUtils.sha256Hex(password);
    }

}
