package dev.gabriel.user.entities;

import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.user.events.UserActivatedEvent;
import dev.gabriel.user.events.UserCreatedEvent;
import dev.gabriel.user.events.UserDeactivatedEvent;
import dev.gabriel.user.events.UserUpdatedEvent;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends AggregateRoot {
    private final UserConfiguration configuration;

    private User(String id, String name, String email, String passwd) {
        super(UserId.create(id));
        this.configuration = UserConfiguration.create(id, name, email, passwd);
    }

    public static User create(String id, String name, String email, String passwd) {
        User user = new User(id, name, email, passwd);
        user.addEvent(new UserCreatedEvent(user));
        return user;
    }

    public void deactivate() {
        if(configuration.isActive) {
            configuration.isActive = false;
            addEvent(new UserDeactivatedEvent(this));
        }
    }

    public void activate() {
        if(!configuration.isActive) {
            configuration.isActive = true;
            addEvent(new UserActivatedEvent(this));
        }
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

    @Override
    public UserId getId() {
        return (UserId) id;
    }

    public void rename(String name) {
        configuration.setName(name);
        addEvent(new UserUpdatedEvent(this));
    }

    public void changeEmail(String email) {
        configuration.setEmail(email);
        addEvent(new UserUpdatedEvent(this));
    }

    public void changePassword(String passwd) {
        configuration.setPasswd((passwd));
        addEvent(new UserUpdatedEvent(this));
    }
}
