package dev.gabriel.entities.bill.expense;

import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.entities.bill.Bill;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Expense extends Bill {
    protected ExpenseCategory category;

    protected Expense(String id, String name, String comment, Money amount, ExpenseCategory category, BillStatus status, Identity userId) {
        super(id, name, comment, amount, status, userId);
        this.category = category;
    }
}