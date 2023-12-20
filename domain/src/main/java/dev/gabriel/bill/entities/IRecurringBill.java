package dev.gabriel.bill.entities;

import java.time.LocalDate;

public interface IRecurringBill {
    void nextPayment(LocalDate date);
    void checkPayments(LocalDate date);
    LocalDate getNextPaymentDate();
    LocalDate getPreviousPaymentDate();
    LocalDate getStartDate();
    Long getCycles();
    Integer getDaysOccurrence();
}
