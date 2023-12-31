package dev.gabriel.recurringbill.models;

import dev.gabriel.recurringbill.events.*;
import dev.gabriel.recurringbill.exceptions.AlreadyPaidAllPeriodsException;
import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.models.AggregateRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class RecurringBillTests {
    RecurringBill populate() {
        return RecurringBill.create(
                UUID.randomUUID().toString(),
                2L,
                20L,
                LocalDate.of(2023, 12, 8)
        );
    }

    @Test
    @DisplayName("Create recurring bill test case: success")
    void createRecurringBillTestCaseSuccess() {
        RecurringBill recurringBill = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, recurringBill);
        Assertions.assertInstanceOf(RecurringBillCreatedEvent.class, recurringBill.getEvents().get(0));
    }

    @Test
    @DisplayName("Change recurring bill total periods test case: success")
    void changeRecurringBillTotalPeriodsTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.changeTotalPeriods(12L);

        Assertions.assertInstanceOf(RecurringBillTotalPeriodsChangedEvent.class, recurringBill.getEvents().get(1));
    }

    @Test
    @DisplayName("Change recurring bill total periods test case: failed")
    void changeRecurringBillTotalPeriodsTestCaseFailed() {
        RecurringBill recurringBill = populate();

        Assertions.assertThrows(RecurringBillValidationException.class, () -> {
            recurringBill.changeTotalPeriods(-20L);
        });
    }

    @Test
    @DisplayName("Change recurring bill days recurrence test case: success")
    void changeRecurringBillDaysRecurrenceTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.changeDaysRecurrence(4L);

        Assertions.assertInstanceOf(RecurringBillDaysRecurrenceChangedEvent.class, recurringBill.getEvents().get(1));
    }

    @Test
    @DisplayName("Change recurring bill days recurrence test case: failed")
    void changeRecurringBillDaysRecurrenceTestCaseFailed() {
        RecurringBill recurringBill = populate();

        Assertions.assertThrows(RecurringBillValidationException.class, () -> {
            recurringBill.changeDaysRecurrence(0L);
        });
    }

    @Test
    @DisplayName("Change recurring bill start date test case: success")
    void changeRecurringBillStartDateTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.changeStartDate(LocalDate.of(2023, 12, 12));

        Assertions.assertInstanceOf(RecurringBillStartDateChangedEvent.class, recurringBill.getEvents().get(1));
    }

    @Test
    @DisplayName("Pay periods test case: success")
    void payPeriodsTestCaseSuccess() {
        RecurringBill recurringBill = populate();

        Assertions.assertEquals(0L, recurringBill.getPaidPeriods().getValue());
        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringBill.nextPaymentDate());

        recurringBill.payPeriods(1L);

        Assertions.assertEquals(1L, recurringBill.getPaidPeriods().getValue());
        Assertions.assertEquals(LocalDate.of(2023, 12, 10), recurringBill.nextPaymentDate());
    }

    @Test
    @DisplayName("Pay periods test case: failed")
    void payPeriodsTestCaseFailed() {
        RecurringBill recurringBill = populate();
        recurringBill.payPeriods(recurringBill.getTotalPeriods().getValue());

        Assertions.assertThrows(AlreadyPaidAllPeriodsException.class, () -> {
            recurringBill.payPeriods(1L);
        });
    }

    @Test
    @DisplayName("Is period paid test case: success")
    void isPeriodPaidTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        boolean returnedValue = recurringBill.isPeriodPaid(LocalDate.of(2023, 12, 8));

        Assertions.assertFalse(returnedValue);
    }

    @Test
    @DisplayName("Restart periods test case: success")
    void restartPeriodsTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.payPeriods(recurringBill.getTotalPeriods().getValue());

        Assertions.assertEquals(recurringBill.getTotalPeriods(), recurringBill.getPaidPeriods());

        recurringBill.restart();

        Assertions.assertNotEquals(recurringBill.getTotalPeriods(), recurringBill.getPaidPeriods());
        Assertions.assertEquals(0L, recurringBill.getPaidPeriods().getValue());
    }

    @Test
    @DisplayName("Delete recurring bill test case: success")
    void deleteRecurringBillTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.delete();

        Assertions.assertInstanceOf(RecurringBillDeletedEvent.class, recurringBill.getEvents().get(1));
    }
}
