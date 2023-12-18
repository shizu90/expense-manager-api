package dev.gabriel.entities.wallet.incomes;

import dev.gabriel.entities.wallet.IRecurringBill;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class RecurringIncome extends Income implements IRecurringBill {
    private Integer daysOccurrence;
    private Date nextPaymentDate;
    private Date previousPaymentDate;
    private Date startDate;
    private Long cycles;

    private RecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence) {
        super(id, name, comment, amount, category);
        this.daysOccurrence = daysOccurrence;
        this.nextPaymentDate = this.createdAt;
        this.previousPaymentDate = this.createdAt;
        this.startDate = this.createdAt;
        this.cycles = 1L;
    }

    private RecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, Date startDate) {
        super(id, name, comment, amount, category);
        this.daysOccurrence = daysOccurrence;
        this.nextPaymentDate = this.createdAt;
        this.previousPaymentDate = this.createdAt;
        this.startDate = startDate;
        this.cycles = 1L;
    }

    protected static RecurringIncome create(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence) {
        return new RecurringIncome(id, name, comment, amount, category, daysOccurrence);
    }

    protected static RecurringIncome create(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, Date startDate) {
        return new RecurringIncome(id, name, comment, amount, category, daysOccurrence, startDate);
    }

    public void calculateNextPaymentDate() {
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
