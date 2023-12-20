package dev.gabriel.user.entities;

import dev.gabriel.shared.entities.AggregateRoot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends AggregateRoot {
    private final UserConfiguration configuration;

    private User(String id, String name, String email, String passwd) {
        super(id);
        this.configuration = UserConfiguration.create(id, name, email, passwd);
    }

    public static User create(String id, String name, String email, String passwd) {
        return new User(id, name, email, passwd);
    }

    public String getName() {
        return configuration.getName();
    }

    public String getEmail() {
        return configuration.getEmail();
    }

    public String getPassword() {
        return configuration.getPasswd();
    }

    public void changeName(String name) {
        configuration.setName(name);
    }

    public void changeEmail(String email) {
        configuration.setEmail(email);
    }

    public void changePassword(String passwd) {
        configuration.setPasswd((passwd));
    }
}
