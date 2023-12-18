package dev.gabriel.entities.user;

import dev.gabriel.primitives.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserConfiguration extends Entity {
    protected String name;
    protected String email;
    protected String passwd;
    protected boolean isActive;
    protected Date createdAt;

    private UserConfiguration(String id, String name, String email, String passwd) {
        super(id);
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.createdAt = new Date();
        this.isActive = true;
    }

    public static UserConfiguration create(String id, String name, String email, String passwd) {
        return new UserConfiguration(id, name, email, passwd);
    }
}
