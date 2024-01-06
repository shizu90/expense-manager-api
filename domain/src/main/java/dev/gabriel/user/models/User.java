package dev.gabriel.user.models;

import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.events.*;
import dev.gabriel.user.exceptions.UserAlreadyDeletedException;
import dev.gabriel.user.valueobjects.Email;
import dev.gabriel.user.valueobjects.Password;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.user.valueobjects.Username;
import lombok.Getter;

@Getter
public class User extends AggregateRoot {
    private Email email;
    private Username name;
    private Password password;
    private UserConfiguration configuration;
    private Boolean isDeleted;

    private User(String id, String email, String name, String password, CurrencyCode defaultCurrencyCode, UserLanguage defaultUserLanguage) {
        super(UserId.create(id));
        raiseEvent(new UserCreatedEvent(
                UserId.create(id),
                getNextVersion(),
                Username.create(name),
                Email.create(email),
                Password.create(password),
                defaultCurrencyCode,
                defaultUserLanguage)
        );
    }

    public static User create(String id, String email, String name, String password, CurrencyCode defaultCurrencyCode, UserLanguage defaultUserLanguage) {
        return new User(id, email, name, password, defaultCurrencyCode, defaultUserLanguage);
    }

    public void rename(String name) {
        raiseEvent(new UserRenamedEvent(getId(), getNextVersion(), Username.create(name)));
    }

    public void changeEmail(String email) {
        raiseEvent(new UserEmailChangedEvent(getId(), getNextVersion(), Email.create(email)));
    }

    public void changePassword(String password) {
        raiseEvent(new UserPasswordChangedEvent(getId(), getNextVersion(), Password.create(password)));
    }

    public void changeDefaultCurrencyCode(CurrencyCode currencyCode) {
        raiseEvent(new UserDefaultCurrencyCodeChangedEvent(getId(), getNextVersion(), currencyCode));
    }

    public void changeDefaultUserLanguage(UserLanguage userLanguage) {
        raiseEvent(new UserDefaultLanguageChangedEvent(getId(), getNextVersion(), userLanguage));
    }

    public void delete() {
        if(isDeleted) {
            throw new UserAlreadyDeletedException(getId().getValue());
        }else raiseEvent(new UserDeletedEvent(getId(), getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(UserCreatedEvent event) {
        this.name = event.getUsername();
        this.email = event.getEmail();
        this.password = event.getPassword();
        this.configuration = UserConfiguration.create(event.getAggregateId().getValue(), event.getDefaultCurrencyCode(), event.getDefaultLanguage());
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(UserRenamedEvent event) {
        this.name = event.getName();
    }

    @SuppressWarnings("unused")
    private void apply(UserEmailChangedEvent event) {
        this.email = event.getEmail();
    }

    @SuppressWarnings("unused")
    private void apply(UserPasswordChangedEvent event) {
        this.password = event.getPassword();
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
