package dev.gabriel.entities.wallet;

import java.util.Date;

public interface IRecurringBill {
    void calculateNextPaymentDate();
    Date getNextPaymentDate();
    Date getPreviousPaymentDate();
    Date getStartDate();
    Long getCycles();
    Integer getDaysOccurrence();
    Long countCycles(Date today);
}
