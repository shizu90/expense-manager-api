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
    private Boolean finished;
    private Currency totalAmount;

    private Budget(String id, String name, String description, CurrencyCode currencyCode, UserId userId) {
        super(BudgetId.create(id));
        this.name = BudgetName.create(name);
        this.description = BudgetDescription.create(description);
        this.userId = userId;
        this.bills = new ArrayList<>();
        this.finished = false;
        this.totalAmount = Currency.create(BigDecimal.ZERO, currencyCode);
    }

    public static Budget create(String id, String name, String comment, CurrencyCode currencyCode, UserId userId) {
        Budget billGroup = new Budget(id, name, comment, currencyCode, userId);
        billGroup.raiseEvent(new BudgetCreatedEvent(billGroup.getId()));
        return billGroup;
    }

    public void rename(String name) {
        this.name = BudgetName.create(name);
        updatedAt = Instant.now();
        raiseEvent(new BudgetRenamedEvent(getId()));
    }

    public void editDescription(String description) {
        this.description = BudgetDescription.create(description);
        updatedAt = Instant.now();
        raiseEvent(new BudgetDescriptionEditedEvent(getId()));
    }

    public void changeCurrencyCode(CurrencyCode currencyCode) {
        this.totalAmount = Currency.create(totalAmount.getValue(), currencyCode);
        updatedAt = Instant.now();
    }

    public void addBill(Bill bill) {
        Optional<BudgetItem> alreadyInBill = bills.stream().filter(item -> item.getBillId().equals(bill.getId())).findFirst();
        if(alreadyInBill.isPresent()) {
            throw new BudgetItemAlreadyAddedException();
        }
        totalAmount = totalAmount.add(bill.getAmount());
        bills.add(BudgetItem.create(UUID.randomUUID().toString(), bill.getId(), getId()));
        updatedAt = Instant.now();
        raiseEvent(new BudgetItemAddedEvent(getId()));
    }

    public void deleteBill(Bill bill) {
        bills.removeIf(item -> item.getBillId().equals(bill.getId()));
        updatedAt = Instant.now();
        raiseEvent(new BudgetItemDeletedEvent(getId()));
    }

    public void clearBills() {
        bills.clear();
    }

    public void delete() {
        if(isDeleted) {
            throw new BudgetAlreadyDeletedException(getId().getValue());
        }else {
            clearBills();
            isDeleted = true;
            raiseEvent(new BudgetDeletedEvent(getId()));
        }
    }

    @Override
    public BudgetId getId() {
        return (BudgetId) id;
    }
}
