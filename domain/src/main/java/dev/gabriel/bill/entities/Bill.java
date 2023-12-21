package dev.gabriel.bill.entities;

import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.bill.events.expense.ExpenseUpdatedEvent;
import dev.gabriel.bill.events.income.IncomeUpdatedEvent;
import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import lombok.Getter;

@Getter
public abstract class Bill extends AggregateRoot {
    protected String name;
    protected String comment;
    protected Money amount;
    protected BillStatus status;
    protected Identity userId;

    protected Bill(BillId id, String name, String comment, Money amount, BillStatus status, Identity userId) {
        super(id);
        this.name = name;
        this.comment = comment;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
    }

    public void rename(String name) {
        this.name = name;
        if(this instanceof Expense expense) {
            addEvent(new ExpenseUpdatedEvent(expense));
        }else {
            addEvent(new IncomeUpdatedEvent((Income) this));
        }
    }

    public void changeComment(String comment) {
        this.comment = comment;
        if(this instanceof Expense expense) {
            addEvent(new ExpenseUpdatedEvent(expense));
        }else {
            addEvent(new IncomeUpdatedEvent((Income) this));
        }
    }

    public void updateStatus(BillStatus status) {
        this.status = status;
        if(this instanceof Expense expense) {
            addEvent(new ExpenseUpdatedEvent(expense));
        }else {
            addEvent(new IncomeUpdatedEvent((Income) this));
        }
    }

    public void changeAmount(Money amount) {
        updateStatus(BillStatus.UNPAID);
        this.amount = amount;
        if(this instanceof Expense expense) {
            addEvent(new ExpenseUpdatedEvent(expense));
        }else {
            addEvent(new IncomeUpdatedEvent((Income) this));
        }
    }
}
