package domain;

import org.apache.commons.codec.digest.DigestUtils;

public class User implements Entity<Integer> {
    private Integer id;
    private String username;
    private String passwordHash;

    public User(Integer id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public static String hash(String password) {
        return DigestUtils.sha256Hex(password);
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
}
