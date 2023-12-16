package dev.gabriel.entities;

import java.util.Date;

public interface IRecurringBill {
    void calculateNextPaymentDate();
    Date getNextPaymentDate();
    Date getPreviousPaymentDate();
    Integer getDaysOccurrence();
}
