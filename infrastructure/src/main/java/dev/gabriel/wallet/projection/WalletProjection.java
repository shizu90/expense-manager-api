package dev.gabriel.wallet.projection;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.models.WalletType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "Wallets")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class WalletProjection {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal balance;
    private BigDecimal initialBalance;
    private CurrencyCode currencyCode;
    private Boolean main;
    private Instant lastBalanceUpdate;
    private WalletType type;
    private String ownerId;

    public static WalletProjection create(String id,
                                          String name,
                                          String description,
                                          BigDecimal balance,
                                          BigDecimal initialBalance,
                                          CurrencyCode currencyCode,
                                          Boolean main,
                                          Instant lastBalanceUpdate,
                                          WalletType type,
                                          String ownerId
    ) {
        return new WalletProjection(
                id,
                name,
                description,
                balance,
                initialBalance,
                currencyCode,
                main,
                lastBalanceUpdate,
                type,
                ownerId
        );
    }
}
