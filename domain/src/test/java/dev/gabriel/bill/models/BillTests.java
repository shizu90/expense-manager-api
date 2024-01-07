package dev.gabriel.bill.models;

import dev.gabriel.bill.events.*;
import dev.gabriel.bill.exceptions.BillAlreadyDeletedException;
import dev.gabriel.bill.exceptions.BillValidationException;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BillTests {
    Bill populate() {
        return Bill.create(
                UUID.randomUUID().toString(),
                "Name",
                "Comment",
                BigDecimal.valueOf(30.0),
                CurrencyCode.BRL,
                BillType.EXPENSE,
                WalletId.create(UUID.randomUUID().toString()),
                CategoryId.create(UUID.randomUUID().toString())
        );
    }

    @Test
    @DisplayName("Create bill")
    void createBill() {
        Bill bill = populate();

        Assertions.assertInstanceOf(BillCreatedEvent.class, bill.getEvents().get(0));
        Assertions.assertEquals("Name", bill.getName().getValue());
    }

    @Test
    @DisplayName("Create bill from event stream")
    void createBillFromEventStream() {
        Bill bill = populate();
        List<DomainEvent> events = Arrays.asList(new DomainEvent[] {
            new BillCreatedEvent(
                    bill.getId().getValue(),
                    bill.getBaseVersion(),
                    bill.getName().getValue(),
                    bill.getComment().getValue(),
                    bill.getAmount().getValue(),
                    bill.getAmount().getCurrencyCode(),
                    bill.getType(),
                    bill.getWalletId().getValue(),
                    bill.getCategoryId().getValue()
            )
        });

        Bill billFromEventStream = Bill.create(bill.getId().getValue(), events);

        Assertions.assertEquals(bill.getId(), billFromEventStream.getId());
    }

    @Test
    @DisplayName("Rename bill")
    void renameBillTestCase1() {
        Bill bill = populate();
        bill.rename("NewName");

        Assertions.assertInstanceOf(BillRenamedEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals("NewName", bill.getName().getValue());
    }

    @Test
    @DisplayName("Rename bill should throw BillValidationException")
    void renameBillShouldThrowBillValidationException() {
        Bill bill = populate();

        Assertions.assertThrows(BillValidationException.class, () -> {
            bill.rename(null);
        });
        Assertions.assertEquals(1, bill.getEvents().size());
    }

    @Test
    @DisplayName("Edit bill comment")
    void editBillComment() {
        Bill bill = populate();
        bill.editComment("NewComment");

        Assertions.assertInstanceOf(BillCommentEditedEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals("NewComment", bill.getComment().getValue());
    }

    @Test
    @DisplayName("Edit bill comment should throw BillValidationException")
    void editBillCommentShouldThrowBillValidationException() {
        Bill bill = populate();

        Assertions.assertThrows(BillValidationException.class, () -> {
            bill.editComment(null);
        });
        Assertions.assertEquals(1, bill.getEvents().size());
    }

    @Test
    @DisplayName("Change bill currency code")
    void changeBillCurrencyCode() {
        Bill bill = populate();
        bill.changeCurrencyCode(CurrencyCode.EUR);

        Assertions.assertInstanceOf(BillCurrencyCodeChangedEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals(CurrencyCode.EUR, bill.getAmount().getCurrencyCode());
    }

    @Test
    @DisplayName("Change bill amount")
    void changeBillAmount() {
        Bill bill = populate();
        bill.changeAmount(BigDecimal.valueOf(100.0));

        Assertions.assertInstanceOf(BillAmountChangedEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals(BigDecimal.valueOf(100.0), bill.getAmount().getValue());
    }

    @Test
    @DisplayName("Change bill category")
    void changeBillCategory() {
        Bill bill = populate();
        CategoryId categoryId = CategoryId.create(UUID.randomUUID().toString());
        bill.changeCategory(categoryId);

        Assertions.assertInstanceOf(BillCategoryChangedEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals(categoryId, bill.getCategoryId());
    }

    @Test
    @DisplayName("Change bill type")
    void changeBillType() {
        Bill bill = populate();
        bill.changeType(BillType.INCOME);

        Assertions.assertInstanceOf(BillTypeChangedEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals(BillType.INCOME, bill.getType());
    }

    @Test
    @DisplayName("Delete bill")
    void deleteBill() {
        Bill bill = populate();
        bill.delete();

        Assertions.assertInstanceOf(BillDeletedEvent.class, bill.getEvents().get(1));
        Assertions.assertTrue(bill.getIsDeleted());
    }

    @Test
    @DisplayName("Delete bill should throw BillAlreadyDeletedException")
    void deleteBillShouldThrowBillAlreadyDeletedException() {
        Bill bill = populate();
        bill.delete();

        Assertions.assertInstanceOf(BillDeletedEvent.class, bill.getEvents().get(1));
        Assertions.assertThrows(BillAlreadyDeletedException.class, bill::delete);
        Assertions.assertEquals(2, bill.getEvents().size());
    }
}
