package dev.gabriel.wallet.models;

import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.shared.valueobjects.UpdatedAt;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.events.*;
import dev.gabriel.wallet.exceptions.WalletAlreadyDeletedException;
import dev.gabriel.wallet.valueobjects.WalletDescription;
import dev.gabriel.wallet.valueobjects.WalletId;
import dev.gabriel.wallet.valueobjects.WalletName;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
public class Wallet extends AggregateRoot {
    private WalletName name;
    private WalletDescription description;
    private Currency balance;
    private Currency initialBalance;
    private Boolean isPrincipal;
    private UpdatedAt lastBalanceUpdate;
    private UserId userId;

    private Wallet(String id, String name, String description, BigDecimal balance, CurrencyCode currencyCode, Boolean isPrincipal, UserId userId) {
        super(WalletId.create(id));
        this.name = WalletName.create(name);
        this.description = WalletDescription.create(description);
        this.balance = Currency.create(balance, currencyCode);
        this.initialBalance = this.balance;
        this.isPrincipal = isPrincipal;
        this.lastBalanceUpdate = updatedAt;
        this.userId = userId;
    }

    public static Wallet create(String id, String name, String description, BigDecimal balance, CurrencyCode currencyCode, Boolean isPrincipal, UserId userId) {
        Wallet wallet = new Wallet(id, name, description, balance, currencyCode, isPrincipal, userId);
        wallet.raiseEvent(new WalletCreatedEvent(wallet.getId()));
        return wallet;
    }

    public void rename(String name) {
        this.name = WalletName.create(name);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new WalletRenamedEvent(getId()));
    }

    public void editDescription(String description) {
        this.description = WalletDescription.create(description);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new WalletDescriptionEditedEvent(getId()));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        this.balance = Currency.create(balance.getValue(), currencyCode);
        this.initialBalance = Currency.create(initialBalance.getValue(), currencyCode);
        updatedAt = UpdatedAt.create(Instant.now());
        raiseEvent(new WalletCurrencyCodeChangedEvent(getId()));
    }

    public void updateBalance(BigDecimal balance) {
        this.balance = Currency.create(balance, this.balance.getCurrencyCode());
        updatedAt = UpdatedAt.create(Instant.now());
        lastBalanceUpdate = updatedAt;
        raiseEvent(new WalletBalanceUpdatedEvent(getId()));
    }

    public void markPrincipal() {
        if(!isPrincipal) {
            isPrincipal = true;
            updatedAt = UpdatedAt.create(Instant.now());
            raiseEvent(new WalletPrincipalMarkedEvent(getId()));
        }
    }

    public void unmarkPrincipal() {
        if(isPrincipal) {
            isPrincipal = false;
            updatedAt = UpdatedAt.create(Instant.now());
            raiseEvent(new WalletPrincipalUnmarkedEvent(getId()));
        }
    }

    public void delete() {
        if(isDeleted) {
            throw new WalletAlreadyDeletedException(getId().getValue());
        }else {
            isDeleted = true;
            raiseEvent(new WalletDeletedEvent(getId()));
        }
    }

    @Override
    public WalletId getId() {
        return (WalletId) id;
    }
}
