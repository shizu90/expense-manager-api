package dev.gabriel.wallet.models;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.events.*;
import dev.gabriel.wallet.exceptions.WalletAlreadyDeletedException;
import dev.gabriel.wallet.valueobjects.WalletDescription;
import dev.gabriel.wallet.valueobjects.WalletId;
import dev.gabriel.wallet.valueobjects.WalletName;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
public class Wallet extends AggregateRoot {
    private WalletName name;
    private WalletDescription description;
    private Currency balance;
    private Currency initialBalance;
    private Boolean main;
    private WalletType type;
    private UserId userId;
    private Boolean isDeleted;

    private Wallet(UUID id,
                   String name,
                   String description,
                   BigDecimal balance,
                   CurrencyCode currencyCode,
                   Boolean main,
                   WalletType type,
                   UserId userId
    ) {
        super(WalletId.create(id));
        WalletName.validate(name);
        WalletDescription.validate(description);
        raiseEvent(new WalletCreatedEvent(
                id,
                getNextVersion(),
                name,
                description,
                balance,
                currencyCode,
                main,
                type,
                userId.getValue()
        ));
    }

    private Wallet(UUID id, List<DomainEvent> events) {
        super(WalletId.create(id), events);
    }

    public static Wallet create(UUID id,
                                String name,
                                String description,
                                BigDecimal balance,
                                CurrencyCode currencyCode,
                                Boolean main,
                                WalletType type,
                                UserId userId
    ) {
        return new Wallet(id, name, description, balance, currencyCode, main, type, userId);
    }

    public static Wallet create(UUID id, List<DomainEvent> events) {
        return new Wallet(id, events);
    }

    public void rename(String name) {
        WalletName.validate(name);
        raiseEvent(new WalletRenamedEvent(getId().getValue(), getNextVersion(), name));
    }

    public void editDescription(String description) {
        WalletDescription.validate(description);
        raiseEvent(new WalletDescriptionEditedEvent(getId().getValue(), getNextVersion(), description));
    }

    public void updateBalance(BigDecimal balance) {
        raiseEvent(new WalletBalanceUpdatedEvent(getId().getValue(), getNextVersion(), balance));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        raiseEvent(new WalletCurrencyCodeChangedEvent(getId().getValue(), getNextVersion(), currencyCode));
    }

    public void changeType(WalletType type) {
        raiseEvent(new WalletTypeChangedEvent(getId().getValue(), getNextVersion(), type));
    }

    public void setMain(Boolean main) {
        raiseEvent(new WalletMainSetEvent(getId().getValue(), getNextVersion(), main));
    }

    public void delete() {
        if(isDeleted) {
            throw new WalletAlreadyDeletedException();
        }else raiseEvent(new WalletDeletedEvent(getId().getValue(), getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(WalletCreatedEvent event) {
        this.name = WalletName.create(event.getName());
        this.description = WalletDescription.create(event.getDescription());
        this.balance = Currency.create(event.getBalance(), event.getCurrencyCode());
        this.initialBalance = balance;
        this.userId = UserId.create(event.getUserId());
        this.main = event.getMain();
        this.type = event.getType();
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(WalletRenamedEvent event) {
        this.name = WalletName.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(WalletDescriptionEditedEvent event) {
        this.description = WalletDescription.create(event.getDescription());
    }

    @SuppressWarnings("unused")
    private void apply(WalletBalanceUpdatedEvent event) {
        this.balance = Currency.create(event.getBalance(), balance.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(WalletCurrencyCodeChangedEvent event) {
        this.balance = Currency.create(balance.getValue(), event.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(WalletTypeChangedEvent event) {
        this.type = event.getType();
    }

    @SuppressWarnings("unused")
    private void apply(WalletMainSetEvent event) {
        this.main = event.getMain();
    }

    @SuppressWarnings("unused")
    private void apply(WalletDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public WalletId getId() {
        return (WalletId) id;
    }
}
