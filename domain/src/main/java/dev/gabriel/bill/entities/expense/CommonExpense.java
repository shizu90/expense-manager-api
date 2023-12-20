package dev.gabriel.bill.entities.expense;

import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.events.expense.ExpenseCreatedEvent;
import dev.gabriel.bill.events.expense.ExpenseRemovedEvent;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

public class CommonExpense extends Expense {
    private CommonExpense(String id, String name, String comment, Money amount, ExpenseCategory category, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
    }

    public static CommonExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, BillStatus status, Identity userId) {
        CommonExpense commonExpense = new CommonExpense(id, name, comment, amount, category, status, userId);
        addEvent(new ExpenseCreatedEvent(commonExpense.identity));
        return commonExpense;
    }

    public void delete() {
        addEvent(new ExpenseRemovedEvent(identity));
    }
}
