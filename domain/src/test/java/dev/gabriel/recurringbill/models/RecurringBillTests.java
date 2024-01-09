package dev.gabriel.recurringbill.models;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.events.*;
import dev.gabriel.recurringbill.exceptions.RecurringBillAlreadyDeletedException;
import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RecurringBillTests {
    RecurringBill populate() {
        return RecurringBill.create(
                UUID.randomUUID(),
                "Name",
                "Comment",
                BigDecimal.valueOf(20.0),
                CurrencyCode.BRL,
                CategoryId.create(UUID.randomUUID()),
                RecurringBillType.INCOME,
                WalletId.create(UUID.randomUUID()),
                ReminderId.create(UUID.randomUUID()),
                2L,
                20L,
                LocalDate.now()
        );
    }

    @Test
    @DisplayName("Create recurring bill")
    void createRecurringBill() {
        RecurringBill recurringBill = populate();

        Assertions.assertInstanceOf(RecurringBillCreatedEvent.class, recurringBill.getEvents().get(0));
        Assertions.assertEquals("Name", recurringBill.getName().getValue());
    }

    @Test
    @DisplayName("Create recurring bill from event stream")
    void createRecurringBillFromEventStream() {
        RecurringBill recurringBill = populate();
        List<DomainEvent> events = Arrays.asList(new DomainEvent[] {
                new RecurringBillCreatedEvent(
                        recurringBill.getId().getValue(),
                        recurringBill.getBaseVersion(),
                        recurringBill.getName().getValue(),
                        recurringBill.getComment().getValue(),
                        recurringBill.getAmount().getValue(),
                        recurringBill.getAmount().getCurrencyCode(),
                        recurringBill.getType(),
                        recurringBill.getCategoryId().getValue(),
                        recurringBill.getWalletId().getValue(),
                        recurringBill.getMaxPeriods().getValue(),
                        recurringBill.getRecurrence().getValue(),
                        recurringBill.getStartDate(),
                        recurringBill.getReminderId().getValue()
                )
        });
        RecurringBill recurringBillFromEventStream = RecurringBill.create(recurringBill.getId().getValue(), events);

        Assertions.assertEquals(recurringBill.getId(), recurringBillFromEventStream.getId());
    }

    @Test
    @DisplayName("Rename recurring bill")
    void renameRecurringBill() {
        RecurringBill recurringBill = populate();
        recurringBill.rename("NewName");

        Assertions.assertInstanceOf(RecurringBillRenamedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals("NewName", recurringBill.getName().getValue());
    }

    @Test
    @DisplayName("Rename recurring bill should throw RecurringBillValidationException")
    void renameRecurringBillShouldThrowRecurringBillValidationException() {
        RecurringBill recurringBill = populate();

        Assertions.assertThrows(RecurringBillValidationException.class, () -> {
            recurringBill.rename(null);
        });
        Assertions.assertEquals(1, recurringBill.getEvents().size());
    }

    @Test
    @DisplayName("Edit comment")
    void editComment() {
        RecurringBill recurringBill = populate();
        recurringBill.editComment("NewComment");

        Assertions.assertInstanceOf(RecurringBillCommentEditedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals("NewComment", recurringBill.getComment().getValue());
    }

    @Test
    @DisplayName("Edit comment should throw RecurringBillValidationException")
    void editCommentShouldThrowRecurringBillValidationException() {
        RecurringBill recurringBill = populate();

        Assertions.assertThrows(RecurringBillValidationException.class, () -> {
            recurringBill.editComment(null);
        });
        Assertions.assertEquals(1, recurringBill.getEvents().size());
    }

    @Test
    @DisplayName("Change amount")
    void changeAmount() {
        RecurringBill recurringBill = populate();
        recurringBill.changeAmount(BigDecimal.valueOf(40.0));

        Assertions.assertInstanceOf(RecurringBillAmountChangedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(BigDecimal.valueOf(40.0), recurringBill.getAmount().getValue());
    }

    @Test
    @DisplayName("Change currency code")
    void changeCurrencyCode() {
        RecurringBill recurringBill = populate();
        recurringBill.changeCurrencyCode(CurrencyCode.USD);

        Assertions.assertInstanceOf(RecurringBillCurrencyCodeChangedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(CurrencyCode.USD, recurringBill.getAmount().getCurrencyCode());
    }

    @Test
    @DisplayName("Change category")
    void changeCategory() {
        RecurringBill recurringBill = populate();
        CategoryId categoryId = CategoryId.create(UUID.randomUUID());
        recurringBill.changeCategory(categoryId);

        Assertions.assertInstanceOf(RecurringBillCategoryChangedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(categoryId, recurringBill.getCategoryId());
    }

    @Test
    @DisplayName("Change type")
    void changeType() {
        RecurringBill recurringBill = populate();
        recurringBill.changeType(RecurringBillType.INCOME);

        Assertions.assertInstanceOf(RecurringBillTypeChangedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(RecurringBillType.INCOME, recurringBill.getType());
    }

    @Test
    @DisplayName("Change recurrence")
    void changeRecurrence() {
        RecurringBill recurringBill = populate();
        recurringBill.changeRecurrence(4L);

        Assertions.assertInstanceOf(RecurringBillRecurrenceChangedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(4L, recurringBill.getRecurrence().getValue());
    }

    @Test
    @DisplayName("Change recurrence should throw RecurringBillValidationException")
    void changeRecurrenceShouldThrowRecurringBillValidationException() {
        RecurringBill recurringBill = populate();

        Assertions.assertThrows(RecurringBillValidationException.class, () -> {
            recurringBill.changeRecurrence(40L);
        });

        Assertions.assertEquals(1, recurringBill.getEvents().size());
    }

    @Test
    @DisplayName("Change max periods")
    void changeMaxPeriods() {
        RecurringBill recurringBill = populate();
        recurringBill.changeMaxPeriods(40L);

        Assertions.assertInstanceOf(RecurringBillMaxPeriodsChangedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(40L, recurringBill.getMaxPeriods().getValue());
    }

    @Test
    @DisplayName("Change max periods should throw RecurringBillValidationException")
    void changeMaxPeriodsShouldThrowRecurringBillValidationException() {
        RecurringBill recurringBill = populate();

        Assertions.assertThrows(RecurringBillValidationException.class, () -> {
            recurringBill.changeMaxPeriods(1L);
        });
        Assertions.assertEquals(1, recurringBill.getEvents().size());
    }

    @Test
    @DisplayName("Change start date")
    void changeStartDate() {
        RecurringBill recurringBill = populate();
        recurringBill.changeStartDate(LocalDate.of(2023, 12, 30));

        Assertions.assertInstanceOf(RecurringBillStartDateChangedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(LocalDate.of(2023, 12, 30), recurringBill.getStartDate());
    }

    @Test
    @DisplayName("Get next payment date")
    void getNextPaymentDate() {
        RecurringBill recurringBill = populate();
        LocalDate nextPaymentDate = recurringBill.getNextPaymentDate();

        Assertions.assertEquals(LocalDate.now().plusDays(2), nextPaymentDate);
    }

    @Test
    @DisplayName("Execute recurring bill")
    void executeRecurringBill() {
        RecurringBill recurringBill = populate();
        recurringBill.execute(2L);

        Assertions.assertInstanceOf(RecurringBillExecutedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(3L, recurringBill.getCurrentPeriods().getValue());
    }

    @Test
    @DisplayName("Restart recurring bill")
    void restartRecurringBill() {
        RecurringBill recurringBill = populate();
        recurringBill.restart();

        Assertions.assertInstanceOf(RecurringBillRestartedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertEquals(1L, recurringBill.getCurrentPeriods().getValue());
    }

    @Test
    @DisplayName("Delete recurring bill")
    void deleteRecurringBill() {
        RecurringBill recurringBill = populate();
        recurringBill.delete();

        Assertions.assertInstanceOf(RecurringBillDeletedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertTrue(recurringBill.getIsDeleted());
    }

    @Test
    @DisplayName("Delete recurring bill should throw RecurringBillAlreadyDeletedException")
    void deleteRecurringBillShouldThrowRecurringBillAlreadyDeletedException() {
        RecurringBill recurringBill = populate();
        recurringBill.delete();

        Assertions.assertInstanceOf(RecurringBillDeletedEvent.class, recurringBill.getEvents().get(1));
        Assertions.assertThrows(RecurringBillAlreadyDeletedException.class, recurringBill::delete);
        Assertions.assertEquals(2, recurringBill.getEvents().size());
    }
}
