package dev.gabriel.bill.entities.expense;

import dev.gabriel.bill.entities.IRecurringBill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.shared.valueobjects.CalendarDate;
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
    private CalendarDate previousPaymentDate;
    private CalendarDate nextPaymentDate;
    private CalendarDate startDate;
    private Long cycles;

    private RecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, BillStatus status, Identity userId) {
        super(id, name, comment, amount, category, status, userId);
        this.daysOccurrence = daysOccurrence;
        this.startDate = CalendarDate.create(getCreatedAt());
        this.previousPaymentDate = startDate;
        this.nextPaymentDate = startDate.addDays(daysOccurrence);
        this.cycles = 0L;
    }

    private RecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, BillStatus status, Identity userId, LocalDate startDate) {
        super(id, name, comment, amount, category, status, userId);
        this.daysOccurrence = daysOccurrence;
        this.startDate = CalendarDate.create(startDate);
        this.previousPaymentDate = this.startDate;
        this.nextPaymentDate = this.startDate.addDays(daysOccurrence);
        this.cycles = 0L;
    }

    public static RecurringExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, BillStatus status, Identity userId) {
        return new RecurringExpense(id, name, comment, amount, category, daysOccurrence, status, userId);
    }

    public static RecurringExpense create(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, BillStatus status, Identity userId, LocalDate startDate) {
        return new RecurringExpense(id, name, comment, amount, category, daysOccurrence, status, userId, startDate);
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
