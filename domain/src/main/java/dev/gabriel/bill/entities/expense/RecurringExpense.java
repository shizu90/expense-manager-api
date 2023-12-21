package dev.gabriel.bill.entities.expense;

import dev.gabriel.bill.entities.IRecurringBill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.events.expense.ExpenseCreatedEvent;
import dev.gabriel.bill.events.expense.ExpenseRemovedEvent;
import dev.gabriel.bill.events.expense.ExpenseUpdatedEvent;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
public class RecurringExpense extends Expense implements IRecurringBill {
    private Integer daysOccurrence;
    private LocalDate previousPaymentDate;
    private LocalDate nextPaymentDate;
    private LocalDate startDate;
    private Long cycles;

    private RecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
        this.daysOccurrence = daysOccurrence;
        this.startDate = createdAt.toLocalDate();
        this.previousPaymentDate = startDate;
        this.nextPaymentDate = startDate.plusDays(daysOccurrence);
        this.cycles = 0L;
    }

    private RecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, BillStatus status, Identity userId, LocalDate startDate) {
        super(id, name, comment, amount, category, status, userId);
        this.daysOccurrence = daysOccurrence;
        this.startDate = startDate;
        this.previousPaymentDate = this.startDate;
        this.nextPaymentDate = this.startDate.plusDays(daysOccurrence);
        this.cycles = 0L;
    }

    public static RecurringExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, BillStatus status, Identity userId) {
        RecurringExpense recurringExpense = new RecurringExpense(id, name, comment, amount, category, daysOccurrence, status, userId);
        recurringExpense.addEvent(new ExpenseCreatedEvent(recurringExpense));
        return recurringExpense;
    }

    public static RecurringExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, BillStatus status, Identity userId, LocalDate startDate) {
        RecurringExpense recurringExpense = new RecurringExpense(id, name, comment, amount, category, daysOccurrence, status, userId, startDate);
        recurringExpense.addEvent(new ExpenseCreatedEvent(recurringExpense));
        return recurringExpense;
    }

    public void remove() {
        addEvent(new ExpenseRemovedEvent(this));
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
