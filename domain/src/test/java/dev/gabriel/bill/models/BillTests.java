package dev.gabriel.bill.models;

import dev.gabriel.bill.events.BillCommentEditedEvent;
import dev.gabriel.bill.events.BillCreatedEvent;
import dev.gabriel.bill.events.BillRenamedEvent;
import dev.gabriel.bill.exceptions.BillValidationException;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
    @DisplayName("Create bill test case 1")
    void createBillTestCase1() {
        Bill bill = populate();

        Assertions.assertInstanceOf(BillCreatedEvent.class, bill.getEvents().get(0));
        Assertions.assertEquals("Name", bill.getName().getValue());
    }

    @Test
    @DisplayName("Rename bill test case 1")
    void renameBillTestCase1() {
        Bill bill = populate();
        bill.rename("NewName");

        Assertions.assertInstanceOf(BillCreatedEvent.class, bill.getEvents().get(0));
        Assertions.assertInstanceOf(BillRenamedEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals("NewName", bill.getName().getValue());
    }

    @Test
    @DisplayName("Edit bill comment test case 1")
    void editBillCommentTestCase1() {
        Bill bill = populate();
        bill.editComment("NewComment");

        Assertions.assertInstanceOf(BillCreatedEvent.class, bill.getEvents().get(0));
        Assertions.assertInstanceOf(BillCommentEditedEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals("NewComment", bill.getComment().getValue());
    }

    @Test
    @DisplayName("Edit bill comment test case 2")
    void editBillCommentTestCase2() {
        Bill bill = populate();

        Assertions.assertThrows(BillValidationException.class, () -> {
            bill.editComment(null);
        });
        Assertions.assertEquals(1, bill.getEvents().size());
    }
}
