package dev.gabriel.bill.models;

import dev.gabriel.bill.valueobjects.BillRecurrenceId;
import dev.gabriel.shared.models.Entity;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
public class BillRecurrence extends Entity {
    private Long daysRecurrence;
    private LocalDate startDate;
    private Long paidPeriods;
    private LocalDate lastPaidDate;

    private BillRecurrence(String id, Long daysRecurrence, LocalDate startDate) {
        super(BillRecurrenceId.create(id));
        this.daysRecurrence = daysRecurrence;
        this.startDate = startDate;
        this.lastPaidDate = startDate;
        this.paidPeriods = 0L;
    }

    public static BillRecurrence create(String id, Long days, LocalDate startDate) {
        return new BillRecurrence(id, days, startDate);
    }

    public Long countPeriods(LocalDate date) {
        if(date.equals(startDate)) return 1L;

        long days = ChronoUnit.DAYS.between(startDate, date);

        return (days + daysRecurrence - 1) / daysRecurrence;
    }

    public LocalDate nextPaymentDate() {
        return startDate.plusDays((paidPeriods * daysRecurrence));
    }

    public void incrementPeriod(long n) {
        this.paidPeriods = paidPeriods + n;
    }

    public Long getPeriodsToPay() {
        LocalDate today = LocalDate.now();
        long periods = countPeriods(today);

        return periods - paidPeriods;
    }

    @Override
    public BillRecurrenceId getId() {
        return (BillRecurrenceId) id;
    }
}
