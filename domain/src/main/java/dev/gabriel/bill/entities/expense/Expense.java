package dev.gabriel.bill.entities.expense;

import dev.gabriel.bill.entities.Bill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.valueobjects.ExpenseId;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Expense extends Bill {
    protected ExpenseCategory category;

    protected Expense(String id, String name, String comment, Money amount, ExpenseCategory category, BillStatus status, Identity userId) {
        super(ExpenseId.create(id), name, comment, amount, status, userId);
        this.category = category;
    }

    @Override
    public ExpenseId getId() {
        return (ExpenseId) id;
    }
}