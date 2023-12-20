package dev.gabriel.entities.user;

import dev.gabriel.primitives.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserConfiguration extends Entity {
    protected String name;
    protected String email;
    protected String passwd;
    protected boolean isActive;

    private UserConfiguration(String id, String name, String email, String passwd) {
        super(id);
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.isActive = true;
    }

    protected static UserConfiguration create(String id, String name, String email, String passwd) {
        return new UserConfiguration(id, name, email, passwd);
    }
}
