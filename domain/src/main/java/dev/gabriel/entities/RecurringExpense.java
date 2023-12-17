package dev.gabriel.entities;

import dev.gabriel.entities.enums.ExpenseCategory;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class RecurringExpense extends Expense implements IRecurringBill {
    private Integer daysOccurrence;
    private Date previousPaymentDate;
    private Date nextPaymentDate;

    private RecurringExpense(Long id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence) {
        super(id, name, comment, amount, category);
        this.daysOccurrence = daysOccurrence;
        this.previousPaymentDate = this.createdAt;
        this.nextPaymentDate = this.createdAt;
    }

    public static RecurringExpense create(Long id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence) {
        return new RecurringExpense(id, name, comment, amount, category, daysOccurrence);
    }

    public void calculateNextPaymentDate() {
        if(!new Date().equals(nextPaymentDate)) return;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nextPaymentDate);
        calendar.add(Calendar.DATE, daysOccurrence);
        previousPaymentDate = nextPaymentDate;
        nextPaymentDate = calendar.getTime();
        calendar.clear();
    }
}
