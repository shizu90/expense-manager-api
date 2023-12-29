package dev.gabriel.bill.models;

import dev.gabriel.bill.events.BillPaidEvent;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class BillRecurrenceTests {
    Bill populate() {
        return Bill.create(
                UUID.randomUUID().toString(),
                "Name",
                "Comment",
                Currency.create(BigDecimal.valueOf(10.0), CurrencyType.BRL),
                BillStatus.UNPAID,
                BillType.OUT,
                UserId.create(UUID.randomUUID().toString()),
                2L,
                LocalDate.now()
        );
    }

    @Test
    @DisplayName("Pay recurrence bill test case: success")
    void payRecurrenceBillTestCaseSuccess() {
        Bill bill = populate();
        Currency amountToPay = bill.pay();

        Assertions.assertInstanceOf(BillPaidEvent.class, bill.getEvents().get(1));
        Assertions.assertEquals(BillStatus.PAID, bill.getStatus());
        Assertions.assertEquals(LocalDate.now().plusDays(2), bill.getNextPaymentDate());
    }

    @Test
    @DisplayName("Check recurrence bill test case: success")
    void checkRecurrenceBillTestCaseSuccess() {
        Bill bill = populate();
        bill.checkRecurrence();

        Assertions.assertEquals(BillStatus.UNPAID, bill.getStatus());
    }

    @Test
    @DisplayName("Check recurrence bill test case: success 2")
    void checkRecurrenceBillTestCaseSuccess2() {
        Bill bill = populate();
        bill.pay();
        bill.checkRecurrence();

        Assertions.assertEquals(BillStatus.PAID, bill.getStatus());
    }
}
