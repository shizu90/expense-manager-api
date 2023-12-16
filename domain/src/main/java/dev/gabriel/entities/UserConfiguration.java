package dev.gabriel.entities;

import dev.gabriel.primitives.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserConfiguration extends Entity {
    private String name;
    private String email;
    private String passwd;
    private Date createdAt;

    private UserConfiguration(Long id, String name, String email, String passwd) {
        super(id);
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.createdAt = new Date();
    }

    public static UserConfiguration create(Long id, String name, String email, String passwd) {
        return new UserConfiguration(id, name, email, passwd);
    }
}
