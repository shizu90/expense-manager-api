package dev.gabriel.budget.models;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.budget.events.*;
import dev.gabriel.budget.exceptions.BudgetAlreadyDeletedException;
import dev.gabriel.budget.exceptions.BudgetItemAlreadyAddedException;
import dev.gabriel.budget.valueobjects.BudgetDescription;
import dev.gabriel.budget.valueobjects.BudgetId;
import dev.gabriel.budget.valueobjects.BudgetName;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
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
        raiseEvent(new BudgetCreatedEvent(
                BudgetId.create(id),
                getNextVersion(),
                BudgetName.create(name),
                BudgetDescription.create(description),
                userId,
                Currency.create(BigDecimal.ZERO, currencyCode))
        );
    }

    public static Budget create(String id, String name, String comment, CurrencyCode currencyCode, UserId userId) {
        return new Budget(id, name, comment, currencyCode, userId);
    }

    public void rename(String name) {
        raiseEvent(new BudgetRenamedEvent(getId(), getNextVersion(), BudgetName.create(name)));
    }

    public void editDescription(String description) {
        raiseEvent(new BudgetDescriptionEditedEvent(getId(), getNextVersion(), BudgetDescription.create(description)));
    }

    public void addBill(Bill bill) {
        Optional<BudgetItem> alreadyInBill = bills.stream().filter(item -> item.getBillId().equals(bill.getId())).findFirst();
        if(alreadyInBill.isPresent()) {
            throw new BudgetItemAlreadyAddedException();
        }
        raiseEvent(new BudgetItemAddedEvent(getId(), getNextVersion(), bill.getId(), bill.getAmount()));
    }

    public void deleteBill(Bill bill) {
        raiseEvent(new BudgetItemDeletedEvent(getId(), getNextVersion(), bill.getId(), bill.getAmount()));
    }

    public void clearBills() {
        raiseEvent(new BudgetItemsClearedEvent(getId(), getNextVersion()));
    }

    public void delete() {
        if(isDeleted) {
            throw new BudgetAlreadyDeletedException(getId().getValue());
        }else {
            clearBills();
            raiseEvent(new BudgetDeletedEvent(getId(), getNextVersion()));
        }
    }

    @SuppressWarnings("unused")
    private void apply(BudgetCreatedEvent event) {
        this.name = event.getName();
        this.description = event.getDescription();
        this.totalAmount = event.getTotalAmount();
        this.bills = new ArrayList<>();
        this.isDeleted = false;
    }

    @SuppressWarnings("unused")
    private void apply(BudgetRenamedEvent event) {
        this.name = event.getName();
    }

    @SuppressWarnings("unused")
    private void apply(BudgetDescriptionEditedEvent event) {
        this.description = event.getDescription();
    }

    @SuppressWarnings("unused")
    private void apply(BudgetItemAddedEvent event) {
        this.totalAmount = totalAmount.add(event.getAmount());
        bills.add(BudgetItem.create(UUID.randomUUID().toString(), event.getBillId(), getId()));
    }

    @SuppressWarnings("unused")
    private void apply(BudgetItemDeletedEvent event) {
        this.totalAmount = totalAmount.subtract(event.getAmount());
        bills.removeIf(item -> item.getBillId().equals(event.getBillId()));
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
