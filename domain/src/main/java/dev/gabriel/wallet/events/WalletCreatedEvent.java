package dev.gabriel.wallet.events;

import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.models.WalletType;
import dev.gabriel.wallet.valueobjects.WalletDescription;
import dev.gabriel.wallet.valueobjects.WalletId;
import dev.gabriel.wallet.valueobjects.WalletName;
import lombok.Getter;

@Getter
public class WalletCreatedEvent extends WalletEvent {
    private final WalletName name;
    private final WalletDescription description;
    private final Currency balance;
    private final Boolean main;
    private final WalletType type;
    private final UserId userId;

    public WalletCreatedEvent(WalletId walletId, Long version, WalletName name, WalletDescription description, Currency balance, Boolean main, WalletType type, UserId userId) {
        super(walletId, version);
        this.name = name;
        this.description = description;
        this.balance = balance;
        this.main = main;
        this.type = type;
        this.userId = userId;
    }
}
