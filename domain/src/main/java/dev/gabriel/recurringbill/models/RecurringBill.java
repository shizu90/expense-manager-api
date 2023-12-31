package dev.gabriel.recurringbill.models;

import dev.gabriel.recurringbill.events.*;
import dev.gabriel.recurringbill.exceptions.AlreadyPaidAllPeriodsException;
import dev.gabriel.recurringbill.valueobjects.DaysRecurrence;
import dev.gabriel.recurringbill.valueobjects.Period;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.models.AggregateRoot;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
public class RecurringBill extends AggregateRoot {
    private DaysRecurrence daysRecurrence;
    private Period totalPeriods;
    private Period paidPeriods;
    private LocalDate startDate;

    private RecurringBill(String id, Long daysRecurrence, Long totalPeriods, LocalDate startDate) {
        super(RecurringBillId.create(id));
        this.daysRecurrence = DaysRecurrence.create(daysRecurrence);
        this.startDate = startDate;
        this.totalPeriods = Period.create(totalPeriods);
        this.paidPeriods = Period.create(0L);
    }

    public static RecurringBill create(String id, Long daysRecurrence, Long totalPeriods, LocalDate startDate) {
        RecurringBill recurringBill = new RecurringBill(id, daysRecurrence, totalPeriods, startDate);
        recurringBill.raiseEvent(new RecurringBillCreatedEvent(recurringBill.getId()));
        return recurringBill;
    }

    public void changeDaysRecurrence(Long daysRecurrence) {
        this.daysRecurrence = DaysRecurrence.create(daysRecurrence);
        raiseEvent(new RecurringBillDaysRecurrenceChangedEvent(getId()));
    }

    public void changeTotalPeriods(Long totalPeriods) {
        this.totalPeriods = Period.create(totalPeriods);
        raiseEvent(new RecurringBillTotalPeriodsChangedEvent(getId()));
    }

    public void changeStartDate(LocalDate startDate) {
        this.startDate = startDate;
        raiseEvent(new RecurringBillStartDateChangedEvent(getId()));
    }

    public LocalDate nextPaymentDate() {
        if(paidPeriods.equals(totalPeriods)) throw new AlreadyPaidAllPeriodsException();

        return startDate.plusDays((paidPeriods.getValue() * daysRecurrence.getValue()));
    }

    private Long countPeriods(LocalDate date) {
        if(date.equals(startDate)) return 1L;

        long days = ChronoUnit.DAYS.between(startDate, date);

        return (days + daysRecurrence.getValue() - 1) / daysRecurrence.getValue();
    }

    public boolean isPeriodPaid(LocalDate date) {
        long periods = countPeriods(date);

        return periods == paidPeriods.getValue();
    }

    public void payPeriods(Long numberOfPeriods) {
        if(paidPeriods.equals(totalPeriods)) throw new AlreadyPaidAllPeriodsException();

        if(numberOfPeriods > totalPeriods.getValue()) {
            paidPeriods = paidPeriods.increment(totalPeriods.getValue());
        } else paidPeriods = paidPeriods.increment(numberOfPeriods);
    }

    public void restart() {
        paidPeriods = paidPeriods.decrement(paidPeriods.getValue());
    }

    public void delete() {
        raiseEvent(new RecurringBillDeletedEvent(getId()));
    }

    @Override
    public RecurringBillId getId() {
        return (RecurringBillId) id;
    }
}
