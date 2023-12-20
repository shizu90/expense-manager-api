package dev.gabriel.bill.entities.expense;

import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

public class CommonExpense extends Expense {
    private CommonExpense(String id, String name, String comment, Money amount, ExpenseCategory category, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
    }

    public static CommonExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, BillStatus status, Identity userId) {
        return new CommonExpense(id, name, comment, amount, category, status, userId);
    }
}
