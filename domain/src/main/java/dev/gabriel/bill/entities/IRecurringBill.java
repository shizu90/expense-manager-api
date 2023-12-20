package dev.gabriel.bill.entities;

import dev.gabriel.shared.valueobjects.CalendarDate;

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
