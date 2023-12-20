package dev.gabriel.shared.valueobjects;

import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.shared.entities.ValueObject;
import dev.gabriel.shared.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class MoneyTest {
    @Test
    @DisplayName("Should create money successfully.")
    public void createMoneyTestCase() {
        Money money = Money.create(BigDecimal.valueOf(20), CurrencyType.BRL);

        Assertions.assertInstanceOf(ValueObject.class, money);
    }

    @Test
    @DisplayName("Should add money properly.")
    public void addMoneyTestCase() {
        Money money1 = Money.create(BigDecimal.valueOf(20), CurrencyType.BRL);
        Money money2 = Money.create(BigDecimal.valueOf(40), CurrencyType.BRL);
        Money money3 = money1.add(money2);

        Assertions.assertEquals(BigDecimal.valueOf(60), money3.getAtomicValues().get(0));
    }

    @Test
    @DisplayName("Should subtract money properly.")
    public void subtractMoneyTestCase() {
        Money money1 = Money.create(BigDecimal.valueOf(20), CurrencyType.BRL);
        Money money2 = Money.create(BigDecimal.valueOf(10), CurrencyType.BRL);
        Money money3 = money1.subtract(money2);

        Assertions.assertEquals(BigDecimal.valueOf(10), money3.getAtomicValues().get(0));
    }

    @Test
    @DisplayName("Should multiply money properly.")
    public void multiplyMoneyTestCase() {
        Money money1 = Money.create(BigDecimal.valueOf(20), CurrencyType.BRL);
        Money money2 = money1.multiply(4);

        Assertions.assertEquals(BigDecimal.valueOf(80.0), money2.getAtomicValues().get(0));
    }
}
