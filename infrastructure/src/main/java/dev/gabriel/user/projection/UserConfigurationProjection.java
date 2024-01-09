package dev.gabriel.user.projection;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.models.UserLanguage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class UserConfigurationProjection {
    private CurrencyCode defaultCurrencyCode;
    private UserLanguage defaultUserLanguage;
}
