package dev.gabriel.entities.wallet.expenses;

import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.entities.wallet.Bill;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Expense extends Bill {
    protected ExpenseCategory category;

    protected Expense(String id, String name, String comment, Money amount, ExpenseCategory category) {
        super(id, name, comment, amount);
        this.category = category;
    }
}