package dev.gabriel.entities;

import dev.gabriel.entities.enums.ExpenseCategory;

public class CommonExpense extends Expense {
    private CommonExpense(Long id, String name, String comment, Double amount, ExpenseCategory category) {
        super(id, name, comment, amount, category);
    }

    public static CommonExpense create(Long id, String name, String comment, Double amount, ExpenseCategory category) {
        return new CommonExpense(id, name, comment, amount, category);
    }
}
