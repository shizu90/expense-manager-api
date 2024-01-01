package dev.gabriel.budget.models;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillStatus;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.budget.events.*;
import dev.gabriel.budget.exceptions.BudgetValidationException;
import dev.gabriel.budget.exceptions.BudgetItemAlreadyAddedException;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class BudgetTests {
    Budget populate() {
        return Budget.create(
                UUID.randomUUID().toString(),
                "Name",
                "Comment",
                CurrencyCode.BRL,
                UserId.create(UUID.randomUUID().toString())
        );
    }

    Bill populate(UserId userId) {
        return Bill.create(
                UUID.randomUUID().toString(),
                "Name",
                "Comment",
                BigDecimal.valueOf(20.0),
                CurrencyCode.BRL,
                BillStatus.UNPAID,
                BillType.IN,
                userId,
                null,
                null
        );
    }

    @Test
    @DisplayName("Create budget test case: success")
    void createBudgetTestCaseSuccess() {
        Budget budget = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, budget);
        Assertions.assertInstanceOf(BudgetCreatedEvent.class, budget.getEvents().get(0));
    }

    @Test
    @DisplayName("Rename budget test case: success")
    void renameBudgetTestCaseSuccess() {
        Budget budget = populate();
        budget.rename("CoolName");

        Assertions.assertInstanceOf(BudgetRenamedEvent.class, budget.getEvents().get(1));
    }

    @Test
    @DisplayName("Rename budget test case: failed")
    void renameBudgetTestCaseFailed() {
        Budget budget = populate();

        Assertions.assertThrows(BudgetValidationException.class, () -> {
            budget.rename(null);
        });
    }

    @Test
    @DisplayName("Edit bill group comment test case: success")
    void editBudgetDescriptionTestCaseSuccess() {
        Budget budget = populate();
        budget.editDescription("CoolComment");

        Assertions.assertInstanceOf(BudgetDescriptionEditedEvent.class, budget.getEvents().get(1));
    }

    @Test
    @DisplayName("Edit budget description test case: failed")
    void editBudgetDescriptionTestCaseFailed() {
        Budget budget = populate();

        Assertions.assertThrows(BudgetValidationException.class, () -> {
            budget.editDescription(null);
        });
    }

    @Test
    @DisplayName("Add budget item test case: success")
    void addBudgetItemTestCaseSuccess() {
        Budget budget = populate();
        Bill bill1 = populate(budget.getUserId());
        budget.addBill(bill1);

        Assertions.assertInstanceOf(BudgetItemAddedEvent.class, budget.getEvents().get(1));
    }

    @Test
    @DisplayName("Add budget item test case: failed")
    void addBudgetItemTestCaseFailed() {
        Budget budget = populate();
        Bill bill1 = populate(budget.getUserId());
        budget.addBill(bill1);

        Assertions.assertThrows(BudgetItemAlreadyAddedException.class, () -> {
            budget.addBill(bill1);
        });
    }

    @Test
    @DisplayName("Delete budget item test case: success")
    void deleteBudgetItemTestCaseFailed() {
        Budget budget = populate();
        Bill bill1 = populate(budget.getUserId());
        Bill bill2 = populate(budget.getUserId());
        budget.addBill(bill1);
        budget.addBill(bill2);
        budget.deleteBill(bill2);

        Assertions.assertInstanceOf(BudgetItemDeletedEvent.class, budget.getEvents().get(3));
        Assertions.assertEquals(1, budget.getBills().size());
    }

    @Test
    @DisplayName("Delete budget test case: success")
    void deleteBudgetTestCaseSuccess() {
        Budget budget = populate();
        Bill bill1 = populate(budget.getUserId());
        budget.addBill(bill1);
        budget.delete();

        Assertions.assertInstanceOf(BudgetDeletedEvent.class, budget.getEvents().get(2));
        Assertions.assertEquals(0, budget.getBills().size());
    }
}
