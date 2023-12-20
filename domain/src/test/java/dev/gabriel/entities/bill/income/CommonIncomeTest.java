package dev.gabriel.entities.bill.income;

import dev.gabriel.entities.bill.Bill;
import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.CurrencyType;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.primitives.AggregateRoot;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;
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
                        BillStatus.UNPAID, Identity.create(UUID.randomUUID().toString()));

        Assertions.assertInstanceOf(AggregateRoot.class, commonIncome);
        Assertions.assertInstanceOf(Bill.class, commonIncome);
        Assertions.assertInstanceOf(Income.class, commonIncome);
    }
}
