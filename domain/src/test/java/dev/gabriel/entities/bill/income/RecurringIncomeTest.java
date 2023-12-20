package dev.gabriel.entities.bill.income;

import dev.gabriel.entities.bill.Bill;
import dev.gabriel.entities.bill.IRecurringBill;
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
import java.time.LocalDate;
import java.util.UUID;

public class RecurringIncomeTest {

    //Creates a recurring income entity.
    @Test
    @DisplayName("Should create recurring income successfully.")
    void createRecurringIncomeTestCase() {
        RecurringIncome recurringIncome = RecurringIncome
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL),
                        IncomeCategory.OTHER, 4, BillStatus.UNPAID, Identity.create(UUID.randomUUID().toString()));

        Assertions.assertInstanceOf(AggregateRoot.class, recurringIncome);
        Assertions.assertInstanceOf(Bill.class, recurringIncome);
        Assertions.assertInstanceOf(Income.class, recurringIncome);
        Assertions.assertInstanceOf(IRecurringBill.class, recurringIncome);
    }

    //Will count how many cycles have till given LocalDate and pay it.
    //Once counting, set previous payment date to given LocalDate and next payment date to given LocalDate + days occurrence.
    //Will set recurring income status to PAID.
    @Test
    @DisplayName("Should get payment of recurring income properly.")
    void nextPaymentTestCase() {
        RecurringIncome recurringIncome = RecurringIncome
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL),
                        IncomeCategory.OTHER, 4, BillStatus.UNPAID,
                        Identity.create(UUID.randomUUID().toString()), LocalDate.of(2023, 12, 8));

        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringIncome.getPreviousPaymentDate().getAtomicValues().get(0));
        Assertions.assertEquals(LocalDate.of(2023, 12, 12), recurringIncome.getNextPaymentDate().getAtomicValues().get(0));

        recurringIncome.nextPayment(LocalDate.of(2023, 12, 12));

        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringIncome.getStartDate().getAtomicValues().get(0));
        Assertions.assertEquals(LocalDate.of(2023, 12, 12), recurringIncome.getPreviousPaymentDate().getAtomicValues().get(0));
        Assertions.assertEquals(LocalDate.of(2023, 12, 16), recurringIncome.getNextPaymentDate().getAtomicValues().get(0));
        Assertions.assertEquals(1, recurringIncome.getCycles());
        Assertions.assertEquals(BillStatus.PAID, recurringIncome.getStatus());
    }

    //Given a LocalDate in parameter, the method will compare current cycles with paid cycles.
    //If current cycles different from paid cycles, set recurring income status to UNPAID.
    //Or else, set recurring income status to PAID.
    @Test
    @DisplayName("Should check payments of recurring income properly.")
    void checkPaymentsTestCase() {
        RecurringIncome recurringIncome = RecurringIncome
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL),
                        IncomeCategory.OTHER, 4, BillStatus.UNPAID,
                        Identity.create(UUID.randomUUID().toString()), LocalDate.of(2023, 12, 8));

        recurringIncome.checkPayments(LocalDate.of(2023, 12, 12));

        Assertions.assertEquals(BillStatus.UNPAID, recurringIncome.getStatus());
    }
}
