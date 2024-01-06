package dev.gabriel.wallet.models;

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

    private Wallet(String id,
                   String name,
                   String description,
                   BigDecimal balance,
                   CurrencyCode currencyCode,
                   Boolean main,
                   WalletType type,
                   UserId userId
    ) {
        super(WalletId.create(id));
        raiseEvent(new WalletCreatedEvent(
                WalletId.create(id),
                getNextVersion(),
                WalletName.create(name),
                WalletDescription.create(description),
                Currency.create(balance, currencyCode),
                main,
                type,
                userId)
        );
    }

    public static Wallet create(String id,
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

    public void rename(String name) {
        raiseEvent(new WalletRenamedEvent(getId(), getNextVersion(), WalletName.create(name)));
    }

    public void editDescription(String description) {
        raiseEvent(new WalletDescriptionEditedEvent(getId(), getNextVersion(), WalletDescription.create(description)));
    }

    public void updateBalance(Currency balance) {
        raiseEvent(new WalletBalanceUpdatedEvent(getId(), getNextVersion(), balance));
    }

    public void changeType(WalletType type) {
        raiseEvent(new WalletTypeChangedEvent(getId(), getNextVersion(), type));
    }

    public void setMain(Boolean main) {
        raiseEvent(new WalletMainSetEvent(getId(), getNextVersion(), main));
    }

    public void delete() {
        if(isDeleted) {
            throw new WalletAlreadyDeletedException(getId().getValue());
        }else raiseEvent(new WalletDeletedEvent(getId(), getNextVersion()));
    }

    @SuppressWarnings("unused")
    private void apply(WalletCreatedEvent event) {
        this.name = event.getName();
        this.description = event.getDescription();
        this.balance = event.getBalance();
        this.initialBalance = event.getBalance();
        this.userId = event.getUserId();
        this.main = event.getMain();
        this.type = event.getType();
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(WalletRenamedEvent event) {
        this.name = event.getName();
    }

    @SuppressWarnings("unused")
    private void apply(WalletDescriptionEditedEvent event) {
        this.description = event.getDescription();
    }

    @SuppressWarnings("unused")
    private void apply(WalletBalanceUpdatedEvent event) {
        this.balance = event.getBalance();
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
