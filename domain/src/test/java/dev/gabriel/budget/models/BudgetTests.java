package dev.gabriel.budget.models;

import dev.gabriel.bill.models.Bill;
import dev.gabriel.bill.models.BillType;
import dev.gabriel.budget.events.*;
import dev.gabriel.budget.exceptions.BudgetAlreadyDeletedException;
import dev.gabriel.budget.exceptions.BudgetItemAlreadyAddedException;
import dev.gabriel.budget.exceptions.BudgetItemNotPresentException;
import dev.gabriel.budget.exceptions.BudgetValidationException;
import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
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

    Bill populateBill() {
        return Bill.create(
                UUID.randomUUID().toString(),
                "Name",
                "Comment",
                BigDecimal.valueOf(40.0),
                CurrencyCode.BRL,
                BillType.EXPENSE,
                WalletId.create(UUID.randomUUID().toString()),
                CategoryId.create(UUID.randomUUID().toString())
        );
    }

    @Test
    @DisplayName("Create budget")
    void createBudget() {
        Budget budget = populate();

        Assertions.assertInstanceOf(BudgetCreatedEvent.class, budget.getEvents().get(0));
        Assertions.assertEquals("Name", budget.getName().getValue());
    }

    @Test
    @DisplayName("Create budget from event stream.")
    void createBudgetFromEventStream() {
        Budget budget = populate();
        List<DomainEvent> events = Arrays.asList(new DomainEvent[] {
                new BudgetCreatedEvent(
                        budget.getId().getValue(),
                        budget.getBaseVersion(),
                        budget.getName().getValue(),
                        budget.getDescription().getValue(),
                        budget.getUserId().getValue(),
                        budget.getTotalAmount().getCurrencyCode()
                )
        });
        Budget budgetFromEventStream = Budget.create(budget.getId().getValue(), events);

        Assertions.assertEquals(budget.getId(), budgetFromEventStream.getId());
    }

    @Test
    @DisplayName("Rename budget")
    void renameBudget() {
        Budget budget = populate();
        budget.rename("NewName");

        Assertions.assertInstanceOf(BudgetRenamedEvent.class, budget.getEvents().get(1));
        Assertions.assertEquals("NewName", budget.getName().getValue());
    }

    @Test
    @DisplayName("Rename budget should throw BudgetValidationException")
    void renameBudgetShouldThrowBudgetValidationException() {
        Budget budget = populate();

        Assertions.assertThrows(BudgetValidationException.class, () -> {
            budget.rename(null);
        });
        Assertions.assertEquals(1, budget.getEvents().size());
    }

    @Test
    @DisplayName("Edit description")
    void editDescription() {
        Budget budget = populate();
        budget.editDescription("NewDescription");

        Assertions.assertInstanceOf(BudgetDescriptionEditedEvent.class, budget.getEvents().get(1));
        Assertions.assertEquals("NewDescription", budget.getDescription().getValue());
    }

    @Test
    @DisplayName("Edit description should throw BudgetValidationException")
    void editDescriptionShouldThrowBudgetValidationException() {
        Budget budget = populate();

        Assertions.assertThrows(BudgetValidationException.class, () -> {
            budget.editDescription(null);
        });
        Assertions.assertEquals(1, budget.getEvents().size());
    }

    @Test
    @DisplayName("Change currency code")
    void changeCurrencyCode() {
        Budget budget = populate();
        budget.changeCurrencyCode(CurrencyCode.EUR);

        Assertions.assertInstanceOf(BudgetCurrencyCodeChangedEvent.class, budget.getEvents().get(1));
        Assertions.assertEquals(CurrencyCode.EUR, budget.getTotalAmount().getCurrencyCode());
    }

    @Test
    @DisplayName("Add bill")
    void addBill() {
        Budget budget = populate();
        Bill bill = populateBill();

        budget.addBill(bill);

        Assertions.assertInstanceOf(BudgetItemAddedEvent.class, budget.getEvents().get(1));
        Assertions.assertEquals(1, budget.getBills().size());
        Assertions.assertEquals(BigDecimal.valueOf(40.0), budget.getTotalAmount().getValue());
    }

    @Test
    @DisplayName("Add bill should throw BudgetItemAlreadyAddedException")
    void addBillShouldThrowBudgetItemAlreadyAddedException() {
        Budget budget = populate();
        Bill bill = populateBill();

        budget.addBill(bill);

        Assertions.assertInstanceOf(BudgetItemAddedEvent.class, budget.getEvents().get(1));
        Assertions.assertEquals(1, budget.getBills().size());
        Assertions.assertThrows(BudgetItemAlreadyAddedException.class, () -> {
            budget.addBill(bill);
        });
        Assertions.assertEquals(1, budget.getBills().size());
        Assertions.assertEquals(2, budget.getEvents().size());
    }

    @Test
    @DisplayName("Delete bill")
    void deleteBill() {
        Budget budget = populate();
        Bill bill = populateBill();

        budget.addBill(bill);
        Assertions.assertInstanceOf(BudgetItemAddedEvent.class, budget.getEvents().get(1));
        Assertions.assertEquals(1, budget.getBills().size());
        budget.deleteBill(bill);
        Assertions.assertInstanceOf(BudgetItemDeletedEvent.class, budget.getEvents().get(2));
        Assertions.assertEquals(0, budget.getBills().size());
        Assertions.assertEquals(BigDecimal.valueOf(0.0), budget.getTotalAmount().getValue());
    }

    @Test
    @DisplayName("Delete bill should throw BudgetItemNotPresentException")
    void deleteBillShouldThrowBudgetItemNotPresentException() {
        Budget budget = populate();
        Bill bill = populateBill();

        Assertions.assertThrows(BudgetItemNotPresentException.class, () -> {
            budget.deleteBill(bill);
        });
        Assertions.assertEquals(1, budget.getEvents().size());
    }

    @Test
    @DisplayName("Delete budget")
    void deleteBudget() {
        Budget budget = populate();
        budget.delete();

        Assertions.assertInstanceOf(BudgetItemsClearedEvent.class, budget.getEvents().get(1));
        Assertions.assertInstanceOf(BudgetDeletedEvent.class, budget.getEvents().get(2));
        Assertions.assertTrue(budget.getIsDeleted());
    }

    @Test
    @DisplayName("Delete budget should throw BudgetAlreadyDeletedException")
    void deleteBudgetShouldThrowBudgetAlreadyDeletedException() {
        Budget budget = populate();
        budget.delete();

        Assertions.assertInstanceOf(BudgetItemsClearedEvent.class, budget.getEvents().get(1));
        Assertions.assertInstanceOf(BudgetDeletedEvent.class, budget.getEvents().get(2));
        Assertions.assertThrows(BudgetAlreadyDeletedException.class, budget::delete);
        Assertions.assertEquals(3, budget.getEvents().size());
    }
}
