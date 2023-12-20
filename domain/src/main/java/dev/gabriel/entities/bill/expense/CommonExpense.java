package dev.gabriel.entities.bill.expense;

import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;

public class CommonExpense extends Expense {
    private CommonExpense(String id, String name, String comment, Money amount, ExpenseCategory category, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
    }

    protected static CommonExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, BillStatus status, Identity userId) {
        return new CommonExpense(id, name, comment, amount, category, status, userId);
    }
}
