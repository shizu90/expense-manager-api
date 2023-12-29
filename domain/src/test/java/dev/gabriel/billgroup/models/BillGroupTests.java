package dev.gabriel.billgroup.models;

import dev.gabriel.bill.exceptions.BillValidationException;
import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillStatus;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.billgroup.events.*;
import dev.gabriel.billgroup.exceptions.BillGroupValidationException;
import dev.gabriel.billgroup.exceptions.BillItemAlreadyAddedException;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class BillGroupTests {
    BillGroup populate() {
        return BillGroup.create(UUID.randomUUID().toString(), "Name", "Comment", UserId.create(UUID.randomUUID().toString()));
    }

    Bill populate(UserId userId) {
        return Bill.create(
                UUID.randomUUID().toString(),
                "Name",
                "Comment",
                Currency.create(BigDecimal.valueOf(20.0), CurrencyType.BRL),
                BillStatus.UNPAID,
                BillType.IN,
                userId
        );
    }

    @Test
    @DisplayName("Create bill group test case: success")
    void createBillGroupTestCaseSuccess() {
        BillGroup billGroup = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, billGroup);
        Assertions.assertInstanceOf(BillGroupCreatedEvent.class, billGroup.getEvents().get(0));
    }

    @Test
    @DisplayName("Rename bill group test case: success")
    void renameBillGroupTestCaseSuccess() {
        BillGroup billGroup = populate();
        billGroup.rename("CoolName");

        Assertions.assertInstanceOf(BillGroupRenamedEvent.class, billGroup.getEvents().get(1));
    }

    @Test
    @DisplayName("Rename bill group test case: failed")
    void renameBillGroupTestCaseFailed() {
        BillGroup billGroup = populate();

        Assertions.assertThrows(BillGroupValidationException.class, () -> {
            billGroup.rename(null);
        });
    }

    @Test
    @DisplayName("Edit bill group comment test case: success")
    void editBillGroupCommentTestCaseSuccess() {
        BillGroup billGroup = populate();
        billGroup.editComment("CoolComment");

        Assertions.assertInstanceOf(BillGroupCommentEditedEvent.class, billGroup.getEvents().get(1));
    }

    @Test
    @DisplayName("Edit bill group comment test case: failed")
    void editBillGroupCommentTestCaseFailed() {
        BillGroup billGroup = populate();

        Assertions.assertThrows(BillGroupValidationException.class, () -> {
            billGroup.editComment(null);
        });
    }

    @Test
    @DisplayName("Add bill item test case: success")
    void addBillItemTestCaseSuccess() {
        BillGroup billGroup = populate();
        Bill bill1 = populate(billGroup.getUserId());
        billGroup.addBill(bill1);

        Assertions.assertInstanceOf(BillItemAddedEvent.class, billGroup.getEvents().get(1));
    }

    @Test
    @DisplayName("Add bill item test case: failed")
    void addBillItemTestCaseFailed() {
        BillGroup billGroup = populate();
        Bill bill1 = populate(billGroup.getUserId());
        billGroup.addBill(bill1);

        Assertions.assertThrows(BillItemAlreadyAddedException.class, () -> {
            billGroup.addBill(bill1);
        });
    }

    @Test
    @DisplayName("Delete bill item test case: success")
    void deleteBillItemTestCaseFailed() {
        BillGroup billGroup = populate();
        Bill bill1 = populate(billGroup.getUserId());
        Bill bill2 = populate(billGroup.getUserId());
        billGroup.addBill(bill1);
        billGroup.addBill(bill2);
        billGroup.deleteBill(bill2);

        Assertions.assertInstanceOf(BillItemDeletedEvent.class, billGroup.getEvents().get(3));
        Assertions.assertEquals(1, billGroup.getBills().size());
    }

    @Test
    @DisplayName("Delete bill group test case: success")
    void deleteBillGroupTestCaseSuccess() {
        BillGroup billGroup = populate();
        Bill bill1 = populate(billGroup.getUserId());
        billGroup.addBill(bill1);
        billGroup.delete();

        Assertions.assertInstanceOf(BillGroupDeletedEvent.class, billGroup.getEvents().get(2));
        Assertions.assertEquals(0, billGroup.getBills().size());
    }
}
