package dev.gabriel.bill.entities.income;

import dev.gabriel.bill.entities.IRecurringBill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.events.income.IncomeCreatedEvent;
import dev.gabriel.bill.events.income.IncomeRemovedEvent;
import dev.gabriel.bill.events.income.IncomeUpdatedEvent;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class RecurringIncome extends Income implements IRecurringBill {
    private Integer daysOccurrence;
    private LocalDate nextPaymentDate;
    private LocalDate previousPaymentDate;
    private LocalDate startDate;
    private Long cycles;

    private RecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
        this.daysOccurrence = daysOccurrence;
        this.startDate = createdAt.toLocalDate();
        this.nextPaymentDate = startDate.plusDays(daysOccurrence);
        this.previousPaymentDate = startDate;
        this.cycles = 0L;
    }

    private RecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, BillStatus status, Identity userId, LocalDate startDate) {
        super(id, name, comment, amount, category, status, userId);
        this.daysOccurrence = daysOccurrence;
        this.startDate = startDate;
        this.previousPaymentDate = this.startDate;
        this.nextPaymentDate = this.startDate.plusDays(daysOccurrence);
        this.cycles = 0L;
    }

    public static RecurringIncome create(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, BillStatus status, Identity userId) {
        RecurringIncome recurringIncome = new RecurringIncome(id, name, comment, amount, category, daysOccurrence, status, userId);
        recurringIncome.addEvent(new IncomeCreatedEvent(recurringIncome));
        return recurringIncome;
    }

    public static RecurringIncome create(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, BillStatus status, Identity userId, LocalDate startDate) {
        RecurringIncome recurringIncome = new RecurringIncome(id, name, comment, amount, category, daysOccurrence, status, userId, startDate);
        recurringIncome.addEvent(new IncomeCreatedEvent(recurringIncome));
        return recurringIncome;
    }

    public void remove() {
        addEvent(new IncomeRemovedEvent(this));
    }

    private long countCycles(LocalDate date) {
        long days = ChronoUnit.DAYS.between(startDate, date);
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

    private void updatePaymentDates(LocalDate date, Long cycles) {
        previousPaymentDate = startDate.plusDays(cycles * daysOccurrence);
        nextPaymentDate = previousPaymentDate.plusDays(daysOccurrence);
    }

    public void checkNewPayments(LocalDate paymentDate) {
        long newCycles = countCycles(paymentDate);

        updatePaymentDates(paymentDate, newCycles);
        if(cycles < newCycles) {
            updateStatus(BillStatus.UNPAID);
        }else updateStatus(BillStatus.PAID);
    }

    public void nextPayment(LocalDate paymentDate) {
        long newCycles = countCycles(paymentDate);
        long days = daysOccurrence * newCycles;
        LocalDate lastPaymentDate = startDate.plusDays(days);

        previousPaymentDate = lastPaymentDate;
        nextPaymentDate = lastPaymentDate.plusDays(daysOccurrence);
        cycles = newCycles;
        updateStatus(BillStatus.PAID);
    }
}
