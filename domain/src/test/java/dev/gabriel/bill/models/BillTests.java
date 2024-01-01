package dev.gabriel.bill.models;

import dev.gabriel.bill.events.*;
import dev.gabriel.bill.exceptions.BillAlreadyPaidException;
import dev.gabriel.bill.exceptions.BillValidationException;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
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
                BigDecimal.valueOf(20.0),
                CurrencyCode.BRL,
                BillStatus.UNPAID,
                BillType.IN,
                UserId.create(UUID.randomUUID().toString()),
                null,
                null
        );
    }

    @Test
    @DisplayName("Create bill test case: success")
    void createBillTestCaseSuccess() {
        Bill bill = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, bill);
        Assertions.assertInstanceOf(BillCreatedEvent.class, bill.getEvents().get(0));
    }

    @Test
    @DisplayName("Rename bill test case: success")
    void renameBillTestCaseSuccess() {
        Bill bill = populate();
        bill.rename("CoolName");

        Assertions.assertInstanceOf(BillRenamedEvent.class, bill.getEvents().get(1));
    }

    @Test
    @DisplayName("Rename bill test case: failed")
    void renameBillTestCaseFailed() {
        Bill bill = populate();

        Assertions.assertThrows(BillValidationException.class, () -> {
            bill.rename(null);
        });
    }

    @Test
    @DisplayName("Edit bill comment test case: success")
    void editBillCommentTestCaseSuccess() {
        Bill bill = populate();
        bill.editComment("CoolComment");

        Assertions.assertInstanceOf(BillCommentEditedEvent.class, bill.getEvents().get(1));
    }

    @Test
    @DisplayName("Edit bill comment test case: failed")
    void editBillCommentTestCaseFailed() {
        Bill bill = populate();

        Assertions.assertThrows(BillValidationException.class, () -> {
            bill.editComment(null);
        });
    }

    @Test
    @DisplayName("Change bill amount test case: success")
    void changeBillAmountTestCaseSuccess() {
        Bill bill = populate();
        bill.changeAmount(BigDecimal.valueOf(80.0));

        Assertions.assertInstanceOf(BillAmountChangedEvent.class, bill.getEvents().get(1));
    }

    @Test
    @DisplayName("Change bill currency code test case: success")
    void changeBillCurrencyCodeTestCaseSuccess() {
        Bill bill = populate();
        bill.changeCurrencyCode(CurrencyCode.EUR);

        Assertions.assertInstanceOf(BillCurrencyCodeChangedEvent.class, bill.getEvents().get(1));
    }

    @Test
    @DisplayName("Change bill category test case: success")
    void changeBillCategoryTestCaseSuccess() {
        Bill bill = populate();
        bill.changeCategory(CategoryId.create(UUID.randomUUID().toString()));

        Assertions.assertInstanceOf(BillCategoryChangedEvent.class, bill.getEvents().get(1));
    }

    @Test
    @DisplayName("Pay bill test case: success")
    void payBillTestCaseSuccess() {
        Bill bill = populate();
        bill.pay();

        Assertions.assertInstanceOf(BillPaidEvent.class, bill.getEvents().get(1));
    }

    @Test
    @DisplayName("Pay bill test case: failed")
    void payBillTestCaseFailed() {
        Bill bill = populate();
        bill.pay();

        Assertions.assertThrows(BillAlreadyPaidException.class, bill::pay);
    }
}
