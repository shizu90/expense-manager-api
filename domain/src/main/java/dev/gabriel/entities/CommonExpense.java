package dev.gabriel.entities;

import dev.gabriel.entities.enums.ExpenseCategory;
import dev.gabriel.valueobjects.Money;

public class CommonExpense extends Expense {
    private CommonExpense(Long id, String name, String comment, Money amount, ExpenseCategory category) {
        super(id, name, comment, amount, category);
    }

    public static CommonExpense create(Long id, String name, String comment, Money amount, ExpenseCategory category) {
        return new CommonExpense(id, name, comment, amount, category);
    }
}
