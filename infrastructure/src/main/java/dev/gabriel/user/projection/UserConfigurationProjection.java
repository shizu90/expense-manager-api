package dev.gabriel.user.projection;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.models.UserLanguage;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users_configuration")
@Data
public class UserConfigurationProjection {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    @Column(name = "default_currency_code")
    private CurrencyCode defaultCurrencyCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "default_user_language")
    private UserLanguage defaultUserLanguage;
}
