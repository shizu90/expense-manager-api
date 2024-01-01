package dev.gabriel.user.models;

import dev.gabriel.shared.models.Entity;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserConfigurationId;
import lombok.Getter;

@Getter
public class UserConfiguration extends Entity {
    protected CurrencyCode defaultCurrencyCode;
    protected UserLanguage defaultUserLanguage;

    private UserConfiguration(String id, CurrencyCode defaultCurrencyCode, UserLanguage defaultUserLanguage) {
        super(UserConfigurationId.create(id));
        this.defaultUserLanguage = defaultUserLanguage;
        this.defaultCurrencyCode = defaultCurrencyCode;
    }

    public static UserConfiguration create(String id, CurrencyCode defaultCurrencyCode, UserLanguage defaultUserLanguage) {
        return new UserConfiguration(id, defaultCurrencyCode, defaultUserLanguage);
    }

    @Override
    public UserConfigurationId getId() {
        return (UserConfigurationId) id;
    }
}
