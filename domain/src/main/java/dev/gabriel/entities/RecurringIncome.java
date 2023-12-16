package dev.gabriel.entities;

import dev.gabriel.entities.enums.IncomeCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class RecurringIncome extends Income implements IRecurringBill {
    private Integer daysOccurrence;
    private Date nextPaymentDate;
    private Date previousPaymentDate;

    private RecurringIncome(Long id, String name, String comment, Double amount, IncomeCategory category, Integer daysOccurrence) {
        super(id, name, comment, amount, category);
        this.daysOccurrence = daysOccurrence;
        this.nextPaymentDate = this.createdAt;
        this.previousPaymentDate = this.createdAt;
    }

    public static RecurringIncome create(Long id, String name, String comment, Double amount, IncomeCategory category, Integer daysOccurrence) {
        return new RecurringIncome(id, name, comment, amount, category, daysOccurrence);
    }

    public void calculateNextPaymentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nextPaymentDate);
        calendar.add(Calendar.DATE, daysOccurrence);
        previousPaymentDate = nextPaymentDate;
        nextPaymentDate = calendar.getTime();
        calendar.clear();
    }
}
