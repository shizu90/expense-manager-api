package dev.gabriel.entities.bill;

import dev.gabriel.valueobjects.CalendarDate;

import java.time.LocalDate;

public interface IRecurringBill {
    void nextPayment(LocalDate date);
    void checkPayments(LocalDate date);
    CalendarDate getNextPaymentDate();
    CalendarDate getPreviousPaymentDate();
    CalendarDate getStartDate();
    Long getCycles();
    Integer getDaysOccurrence();
}
