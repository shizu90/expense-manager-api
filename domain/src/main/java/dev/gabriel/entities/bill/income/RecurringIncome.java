package dev.gabriel.entities.bill.income;

import dev.gabriel.entities.bill.IRecurringBill;
import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.valueobjects.CalendarDate;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class RecurringIncome extends Income implements IRecurringBill {
    private Integer daysOccurrence;
    private CalendarDate nextPaymentDate;
    private CalendarDate previousPaymentDate;
    private CalendarDate startDate;
    private Long cycles;

    private RecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
        this.daysOccurrence = daysOccurrence;
        this.startDate = CalendarDate.create(getCreatedAt());
        this.nextPaymentDate = startDate.addDays(daysOccurrence);
        this.previousPaymentDate = startDate;
        this.cycles = 0L;
    }

    private RecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, BillStatus status, Identity userId, LocalDate startDate) {
        super(id, name, comment, amount, category, status, userId);
        this.daysOccurrence = daysOccurrence;
        this.startDate = CalendarDate.create(startDate);
        this.previousPaymentDate = this.startDate;
        this.nextPaymentDate = this.startDate.addDays(daysOccurrence);
        this.cycles = 0L;
    }

    public static RecurringIncome create(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, BillStatus status, Identity userId) {
        return new RecurringIncome(id, name, comment, amount, category, daysOccurrence, status, userId);
    }

    public static RecurringIncome create(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, BillStatus status, Identity userId, LocalDate startDate) {
        return new RecurringIncome(id, name, comment, amount, category, daysOccurrence, status, userId, startDate);
    }

    public void checkPayments(LocalDate paymentDate) {
        long newCycles = countCycles(paymentDate);
        if(cycles < newCycles) {
            updateStatus(BillStatus.UNPAID);
        }else updateStatus(BillStatus.PAID);
    }

    public void nextPayment(LocalDate paymentDate) {
        long newCycles = countCycles(paymentDate);
        long days = daysOccurrence * newCycles;
        CalendarDate lastPaymentDate = startDate.addDays(days);
        previousPaymentDate = lastPaymentDate;
        nextPaymentDate = lastPaymentDate.addDays(daysOccurrence);
        cycles = newCycles;
        updateStatus(BillStatus.PAID);
    }

    private long countCycles(LocalDate date) {
        long days = ChronoUnit.DAYS.between((LocalDate) startDate.getAtomicValues().get(0), date);

        long newCycles;
        if(cycles > 1L) {
            newCycles = (long) Math.floor((double) days / daysOccurrence) - cycles;
        }else newCycles = (long) Math.floor((double) days / daysOccurrence);

        if(newCycles <= 0L) {
            if(cycles > 1L) {
                newCycles = cycles;
            }else newCycles = 1L;
        }

        return newCycles;
    }
}
