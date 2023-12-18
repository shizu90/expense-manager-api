package dev.gabriel.entities.wallet.expenses;

import dev.gabriel.entities.wallet.IRecurringBill;
import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class RecurringExpense extends Expense implements IRecurringBill {
    private Integer daysOccurrence;
    private Date previousPaymentDate;
    private Date nextPaymentDate;
    private Date startDate;
    private Long cycles;

    private RecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence) {
        super(id, name, comment, amount, category);
        this.daysOccurrence = daysOccurrence;
        this.previousPaymentDate = createdAt;
        this.nextPaymentDate = createdAt;
        this.startDate = createdAt;
        this.cycles = 1L;
    }

    private RecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, Date startDate) {
        super(id, name, comment, amount, category);
        this.daysOccurrence = daysOccurrence;
        this.previousPaymentDate = startDate;
        this.nextPaymentDate = startDate;
        this.cycles = 1L;
    }

    protected static RecurringExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence) {
        return new RecurringExpense(id, name, comment, amount, category, daysOccurrence);
    }

    protected static RecurringExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, Date startDate) {
        return new RecurringExpense(id, name, comment, amount, category, daysOccurrence, startDate);
    }

    public void calculateNextPaymentDate() {
        if(!new Date().equals(nextPaymentDate)) return;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(nextPaymentDate);
        calendar.add(Calendar.DATE, daysOccurrence);

        previousPaymentDate = nextPaymentDate;
        nextPaymentDate = calendar.getTime();

        calendar.clear();
        cycles += 1L;
    }

    public Long countCycles(Date today) {
        long diff = Math.abs(today.getTime() - startDate.getTime());
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        cycles = (long) Math.floor((double) days / daysOccurrence);

        return cycles;
    }
}
