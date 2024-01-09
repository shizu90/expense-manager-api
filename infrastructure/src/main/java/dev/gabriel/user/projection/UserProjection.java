package dev.gabriel.user.projection;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.models.UserLanguage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class UserProjection {
    @Id
    private String id;
    private String email;
    private String name;
    private String password;
    private UserConfigurationProjection configuration;

    public static UserProjection create(String id,
                                        String email,
                                        String name,
                                        String password,
                                        CurrencyCode defaultCurrencyCode,
                                        UserLanguage defaultUserLanguage
    ) {
        return new UserProjection(
                id,
                email,
                name,
                password,
                new UserConfigurationProjection(defaultCurrencyCode, defaultUserLanguage)
        );
    }
}
