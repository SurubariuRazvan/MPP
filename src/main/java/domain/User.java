package domain;

import org.apache.commons.codec.digest.DigestUtils;

public class User extends Entity<Integer> {
    String name;
    String passwordHash;

    public User(Integer id, String name, String passwordHash) {
        super(id);
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public String hash(String password) {
        return DigestUtils.sha256Hex(password);
    }
}
