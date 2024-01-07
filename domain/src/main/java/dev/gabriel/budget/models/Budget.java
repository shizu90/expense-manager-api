package dev.gabriel.budget.models;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.budget.events.*;
import dev.gabriel.budget.exceptions.BudgetAlreadyDeletedException;
import dev.gabriel.budget.exceptions.BudgetItemAlreadyAddedException;
import dev.gabriel.budget.exceptions.BudgetItemNotPresentException;
import dev.gabriel.budget.valueobjects.BudgetDescription;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.budget.valueobjects.BudgetName;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
public class Budget extends AggregateRoot {
    private BudgetName name;
    private BudgetDescription description;
    private UserId userId;
    private List<BudgetItem> bills;
    private Currency totalAmount;
    private Boolean isDeleted;

    private Budget(String id, String name, String description, CurrencyCode currencyCode, UserId userId) {
        super(BudgetId.create(id));
        BudgetName.validate(name);
        BudgetDescription.validate(description);
        raiseEvent(new BudgetCreatedEvent(
                id,
                getNextVersion(),
                name,
                description,
                userId.getValue(),
                currencyCode
        ));
    }

    private Budget(String id, List<DomainEvent> events) {
        super(BudgetId.create(id), events);
    }

    public static Budget create(String id, String name, String comment, CurrencyCode currencyCode, UserId userId) {
        return new Budget(id, name, comment, currencyCode, userId);
    }

    public static Budget create(String id, List<DomainEvent> events) {
        return new Budget(id, events);
    }

    public void rename(String name) {
        BudgetName.validate(name);
        raiseEvent(new BudgetRenamedEvent(getId().getValue(), getNextVersion(), name));
    }

    public void editDescription(String description) {
        BudgetDescription.validate(description);
        raiseEvent(new BudgetDescriptionEditedEvent(getId().getValue(), getNextVersion(), description));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        raiseEvent(new BudgetCurrencyCodeChangedEvent(getId().getValue(), getNextVersion(), currencyCode));
    }

    public void addBill(Bill bill) {
        Optional<BudgetItem> alreadyInBill = bills.stream().filter(item -> item.getBillId().equals(bill.getId())).findFirst();
        if(alreadyInBill.isPresent()) {
            throw new BudgetItemAlreadyAddedException();
        }
        raiseEvent(new BudgetItemAddedEvent(
                getId().getValue(),
                getNextVersion(),
                bill.getId().getValue(),
                bill.getAmount().getValue(),
                bill.getAmount().getCurrencyCode(),
                bill.getType()
        ));
    }

    public void deleteBill(Bill bill) {
        Optional<BudgetItem> alreadyInBill = bills.stream().filter(item -> item.getBillId().equals(bill.getId())).findFirst();
        if(alreadyInBill.isEmpty()) {
            throw new BudgetItemNotPresentException();
        }

        raiseEvent(new BudgetItemDeletedEvent(
                getId().getValue(),
                getNextVersion(),
                bill.getId().getValue(),
                bill.getAmount().getValue(),
                bill.getAmount().getCurrencyCode(),
                bill.getType()
        ));
    }

    public void clearBills() {
        raiseEvent(new BudgetItemsClearedEvent(getId().getValue(), getNextVersion()));
    }

    public void delete() {
        if(isDeleted) {
            throw new BudgetAlreadyDeletedException(getId().getValue());
        }else {
            clearBills();
            raiseEvent(new BudgetDeletedEvent(getId().getValue(), getNextVersion()));
        }
    }

    @SuppressWarnings("unused")
    private void apply(BudgetCreatedEvent event) {
        this.name = BudgetName.create(event.getName());
        this.description = BudgetDescription.create(event.getDescription());
        this.totalAmount = Currency.create(BigDecimal.ZERO, event.getCurrencyCode());
        this.bills = new ArrayList<>();
        this.userId = UserId.create(event.getUserId());
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(BudgetRenamedEvent event) {
        this.name = BudgetName.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(BudgetDescriptionEditedEvent event) {
        this.description = BudgetDescription.create(event.getDescription());
    }

    @SuppressWarnings("unused")
    private void apply(BudgetCurrencyCodeChangedEvent event) {
        this.totalAmount = Currency.create(totalAmount.getValue(), event.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(BudgetItemAddedEvent event) {
        if(event.getType().equals(BillType.EXPENSE)) {
            this.totalAmount = totalAmount.add(Currency.create(event.getAmount(), event.getCurrencyCode()));
        }else {
            this.totalAmount = totalAmount.subtract(Currency.create(event.getAmount(), event.getCurrencyCode()));
        }
        bills.add(BudgetItem.create(UUID.randomUUID().toString(), BillId.create(event.getBillId()), getId()));
    }

    @SuppressWarnings("unused")
    private void apply(BudgetItemDeletedEvent event) {
        if(event.getType().equals(BillType.EXPENSE)) {
            this.totalAmount = totalAmount.subtract(Currency.create(event.getAmount(), event.getCurrencyCode()));
        }else {
            this.totalAmount = totalAmount.add(Currency.create(event.getAmount(), event.getCurrencyCode()));
        }
        bills.removeIf(item -> item.getBillId().equals(BillId.create(event.getBillId())));
    }

    @SuppressWarnings("unused")
    private void apply(BudgetItemsClearedEvent event) {
        this.bills.clear();
        this.totalAmount = Currency.create(BigDecimal.ZERO, totalAmount.getCurrencyCode());
    }

    @SuppressWarnings("unused")
    private void apply(BudgetDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public BudgetId getId() {
        return (BudgetId) id;
    }
}
