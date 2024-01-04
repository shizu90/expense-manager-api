package dev.gabriel.recurringbill.models;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.events.*;
import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class RecurringBillTests {
    RecurringBill populate() {
        return RecurringBill.create(
                UUID.randomUUID().toString(),
                "Name",
                "Comment",
                BigDecimal.valueOf(10.0),
                CurrencyCode.USD,
                CategoryId.create(UUID.randomUUID().toString()),
                RecurringBillType.INCOME,
                WalletId.create(UUID.randomUUID().toString()),
                2L,
                20L,
                LocalDate.of(2023, 12, 20)
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
    @DisplayName("Rename recurring bill test case: success")
    void renameRecurringBillTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.rename("CoolName");

        Assertions.assertInstanceOf(RecurringBillRenamedEvent.class, recurringBill.getEvents().get(1));
    }

    @Test
    @DisplayName("Rename recurring bill test case: failed")
    void renameRecurringBillTestCaseFailed() {
        RecurringBill recurringBill = populate();

        Assertions.assertThrows(RecurringBillValidationException.class, () -> {
            recurringBill.rename(null);
        });
    }

    @Test
    @DisplayName("Edit recurring bill comment test case: success")
    void editRecurringBillCommentTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.editComment("CoolComment");

        Assertions.assertInstanceOf(RecurringBillCommentEditedEvent.class, recurringBill.getEvents().get(1));
    }

    @Test
    @DisplayName("Edit recurring bill comment test case: failed")
    void editRecurringBillCommentTestCaseFailed() {
        RecurringBill recurringBill = populate();

        Assertions.assertThrows(RecurringBillValidationException.class, () -> {
            recurringBill.editComment(null);
        });
    }

    @Test
    @DisplayName("Change recurring bill currency code test case: success")
    void changeRecurringBillCurrencyCodeTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.changeCurrencyCode(CurrencyCode.EUR);

        Assertions.assertInstanceOf(RecurringBillCurrencyCodeChangedEvent.class, recurringBill.getEvents().get(1));
    }

    @Test
    @DisplayName("Change recurring bill category test case: success")
    void changeRecurringBillCategoryTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.changeCategory(CategoryId.create(UUID.randomUUID().toString()));

        Assertions.assertInstanceOf(RecurringBillCategoryChangedEvent.class, recurringBill.getEvents().get(1));
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
    @DisplayName("Run recurring bill period test case: success")
    void runRecurringBillPeriodTestCaseSuccess() {
        RecurringBill recurringBill = populate();

        Assertions.assertEquals(LocalDate.of(2023, 12, 22), recurringBill.getNextPaymentDate());
        Assertions.assertEquals(LocalDate.of(2023, 12, 20), recurringBill.getStartDate());
        Assertions.assertEquals(1L, recurringBill.getCurrentPeriods().getValue());

        recurringBill.execute(3L);

        Assertions.assertEquals(4L, recurringBill.getCurrentPeriods().getValue());
        Assertions.assertEquals(LocalDate.of(2023, 12, 28), recurringBill.getNextPaymentDate());
        Assertions.assertInstanceOf(RecurringBillExecutedEvent.class, recurringBill.getEvents().get(1));
    }

    @Test
    @DisplayName("Restart recurring bill test case: success")
    void restartRecurringBillTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.execute(20L);

        Assertions.assertEquals(20L, recurringBill.getCurrentPeriods().getValue());
        recurringBill.restart();
        Assertions.assertEquals(1L, recurringBill.getCurrentPeriods().getValue());
        Assertions.assertInstanceOf(RecurringBillRestartedEvent.class, recurringBill.getEvents().get(2));
    }

    @Test
    @DisplayName("Delete recurring bill test case: success")
    void deleteRecurringBillTestCaseSuccess() {
        RecurringBill recurringBill = populate();
        recurringBill.delete();

        Assertions.assertInstanceOf(RecurringBillDeletedEvent.class, recurringBill.getEvents().get(1));
    }
}
