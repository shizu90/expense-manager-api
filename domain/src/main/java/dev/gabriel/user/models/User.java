package dev.gabriel.user.models;

import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.events.*;
import dev.gabriel.user.exceptions.UserAlreadyActivatedException;
import dev.gabriel.user.exceptions.UserAlreadyDeactivatedException;
import dev.gabriel.user.exceptions.UserAlreadyDeletedException;
import dev.gabriel.user.valueobjects.Email;
import dev.gabriel.user.valueobjects.Password;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.user.valueobjects.Username;
import lombok.Getter;

import java.time.Instant;

@Getter
public class User extends AggregateRoot {
    private Email email;
    private Username name;
    private Password password;
    private Boolean isActive;
    private UserConfiguration configuration;

    private User(String id, String email, String name, String password, CurrencyCode defaultCurrencyCode, UserLanguage defaultUserLanguage) {
        super(UserId.create(id));
        this.email = Email.create(email);
        this.name = Username.create(name);
        this.password = Password.create(password);
        this.isActive = true;
        this.configuration = UserConfiguration.create(id, defaultCurrencyCode, defaultUserLanguage);
    }

    public static User create(String id, String email, String name, String password, CurrencyCode defaultCurrencyCode, UserLanguage defaultUserLanguage) {
        User user = new User(id, email, name, password, defaultCurrencyCode, defaultUserLanguage);
        user.raiseEvent(new UserCreatedEvent(user.getId()));
        return user;
    }

    public void rename(String name) {
        this.name = Username.create(name);
        raiseEvent(new UserRenamedEvent(getId()));
        updatedAt = Instant.now();
    }

    public void changeEmail(String email) {
        this.email = Email.create(email);
        raiseEvent(new UserEmailChangedEvent(getId()));
        updatedAt = Instant.now();
    }

    public void changePassword(String password) {
        this.password = Password.create(password);
        raiseEvent(new UserPasswordChangedEvent(getId()));
        updatedAt = Instant.now();
    }

    public void changeDefaultCurrencyCode(CurrencyCode currencyCode) {
        this.configuration.defaultCurrencyCode = currencyCode;
        updatedAt = Instant.now();
        raiseEvent(new UserDefaultCurrencyCodeChanged(getId()));
    }

    public void changeDefaultUserLanguage(UserLanguage userLanguage) {
        this.configuration.defaultUserLanguage = userLanguage;
        updatedAt = Instant.now();
        raiseEvent(new UserDefaultLanguageChanged(getId()));
    }

    public void activate() {
        if(isActive) {
            throw new UserAlreadyActivatedException();
        }else {
            isActive = true;
            raiseEvent(new UserActivatedEvent(getId()));
        }
    }

    public void deactivate() {
        if(!isActive) {
            throw new UserAlreadyDeactivatedException();
        }else {
            isActive = false;
            raiseEvent(new UserDeactivatedEvent(getId()));
        }
    }

    public void delete() {
        if(isDeleted) {
            throw new UserAlreadyDeletedException(getId().getValue());
        }else {
            isDeleted = true;
            raiseEvent(new UserDeletedEvent(getId()));
        }
    }

    @Override
    public UserId getId() {
        return (UserId) id;
    }
}
