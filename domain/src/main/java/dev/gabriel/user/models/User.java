package dev.gabriel.user.models;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.events.*;
import dev.gabriel.user.exceptions.UserAlreadyDeletedException;
import dev.gabriel.user.valueobjects.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class User extends AggregateRoot {
    private Email email;
    private Username name;
    private Password password;
    private UserConfiguration configuration;
    private Boolean isDeleted;

    private User(UUID id, String email, String name, String password, CurrencyCode defaultCurrencyCode, UserLanguage defaultUserLanguage) {
        super(UserId.create(id));
        Email.validate(email);
        Username.validate(name);
        Password.validate(password);
        raiseEvent(new UserCreatedEvent(
                id,
                getNextVersion(),
                name,
                email,
                password,
                defaultCurrencyCode,
                defaultUserLanguage
        ));
    }

    private User(UUID id, List<DomainEvent> events) {
        super(UserId.create(id), events);
    }

    public static User create(UUID id, String email, String name, String password, CurrencyCode defaultCurrencyCode, UserLanguage defaultUserLanguage) {
        return new User(id, email, name, password, defaultCurrencyCode, defaultUserLanguage);
    }

    public static User create(UUID id, List<DomainEvent> events) {
        return new User(id, events);
    }

    public void rename(String name) {
        Username.validate(name);
        raiseEvent(new UserRenamedEvent(getId().getValue(), getNextVersion(), name));
    }

    public void changeEmail(String email) {
        Email.validate(email);
        raiseEvent(new UserEmailChangedEvent(getId().getValue(), getNextVersion(), email));
    }

    public void changePassword(String password) {
        Password.validate(password);
        raiseEvent(new UserPasswordChangedEvent(getId().getValue(), getNextVersion(), password));
    }

    public void changeDefaultCurrencyCode(CurrencyCode currencyCode) {
        raiseEvent(new UserDefaultCurrencyCodeChangedEvent(getId().getValue(), getNextVersion(), currencyCode));
    }

    public void changeDefaultUserLanguage(UserLanguage userLanguage) {
        raiseEvent(new UserDefaultLanguageChangedEvent(getId().getValue(), getNextVersion(), userLanguage));
    }

    public void delete() {
        if(isDeleted) {
            throw new UserAlreadyDeletedException();
        }else raiseEvent(new UserDeletedEvent(getId().getValue(), getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(UserCreatedEvent event) {
        this.name = Username.create(event.getUsername());
        this.email = Email.create(event.getEmail());
        this.password = Password.create(event.getPassword());
        this.configuration = UserConfiguration.create(event.getAggregateId(), event.getDefaultCurrencyCode(), event.getDefaultLanguage());
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(UserRenamedEvent event) {
        this.name = Username.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(UserEmailChangedEvent event) {
        this.email = Email.create(event.getEmail());
    }

    @SuppressWarnings("unused")
    private void apply(UserPasswordChangedEvent event) {
        this.password = Password.create(event.getPassword());
    }

    @SuppressWarnings("unused")
    private void apply(UserDefaultCurrencyCodeChangedEvent event) {
        this.configuration.defaultCurrencyCode = event.getCurrencyCode();
    }

    @SuppressWarnings("unused")
    private void apply(UserDefaultLanguageChangedEvent event) {
        this.configuration.defaultUserLanguage = event.getLanguage();
    }

    @SuppressWarnings("unused")
    private void apply(UserDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public UserId getId() {
        return (UserId) id;
    }
}
