package dev.gabriel.entities.wallet.expenses;

import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.valueobjects.Money;

public class CommonExpense extends Expense {
    private CommonExpense(String id, String name, String comment, Money amount, ExpenseCategory category) {
        super(id, name, comment, amount, category);
    }

    protected static CommonExpense create(String id, String name, String comment, Money amount, ExpenseCategory category) {
        return new CommonExpense(id, name, comment, amount, category);
    }
}
