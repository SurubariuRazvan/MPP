package domain;

import org.apache.commons.codec.digest.DigestUtils;

public class User extends Entity<Integer> {
    String username;
    String passwordHash;

    public User(Integer id, String username, String passwordHash) {
        super(id);
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public static String hash(String password) {
        return DigestUtils.sha256Hex(password);
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
}
