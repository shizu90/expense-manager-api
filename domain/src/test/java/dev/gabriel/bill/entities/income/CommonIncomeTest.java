package dev.gabriel.bill.entities.income;

import dev.gabriel.bill.entities.income.CommonIncome;
import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.bill.entities.Bill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.events.income.IncomeCreatedEvent;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.bill.entities.income.IncomeCategory;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class CommonIncomeTest {
    @Test
    @DisplayName("Should create common income successfully.")
    void createCommonIncomeTestCase() {
        CommonIncome commonIncome = CommonIncome
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL), IncomeCategory.OTHER,
                        BillStatus.UNPAID, UserId.create(UUID.randomUUID().toString()));

        Assertions.assertInstanceOf(AggregateRoot.class, commonIncome);
        Assertions.assertInstanceOf(Bill.class, commonIncome);
        Assertions.assertInstanceOf(Income.class, commonIncome);
        Assertions.assertInstanceOf(IncomeCreatedEvent.class, commonIncome.getEvents().get(0));
    }
}
