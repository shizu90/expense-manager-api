package dev.gabriel.user.entities;

import dev.gabriel.shared.entities.Entity;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.user.valueobjects.UserConfigurationId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConfiguration extends Entity {
    protected String name;
    protected String email;
    protected String passwd;
    protected boolean isActive;

    private UserConfiguration(String id, String name, String email, String passwd) {
        super(UserConfigurationId.create(id));
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.isActive = true;
    }

    protected static UserConfiguration create(String id, String name, String email, String passwd) {
        return new UserConfiguration(id, name, email, passwd);
    }

    @Override
    public UserConfigurationId getId() {
        return (UserConfigurationId) id;
    }
}
