package dev.gabriel.valueobjects;

import dev.gabriel.enums.CurrencyType;
import dev.gabriel.primitives.ValueObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    @DisplayName("Should create money successfully.")
    public void createMoneyTestCase() {
        Money money = Money.create(20.0, CurrencyType.BRL);

        Assertions.assertInstanceOf(ValueObject.class, money);
    }

    @Test
    @DisplayName("Should add money properly.")
    public void addMoneyTestCase() {
        Money money1 = Money.create(20.0, CurrencyType.BRL);
        Money money2 = Money.create(40.0, CurrencyType.BRL);
        money1.add(money2);

        Assertions.assertEquals(60.0, money1.getValue());
    }

    @Test
    @DisplayName("Should subtract money properly.")
    public void subtractMoneyTestCase() {
        Money money1 = Money.create(20.0, CurrencyType.BRL);
        Money money2 = Money.create(10.0, CurrencyType.BRL);
        money1.subtract(money2);

        Assertions.assertEquals(10.0, money1.getValue());
    }

    @Test
    @DisplayName("Should multiply money properly.")
    public void multiplyMoneyTestCase() {
        Money money = Money.create(20.0, CurrencyType.BRL);
        money.multiply(4.0);

        Assertions.assertEquals(80.0, money.getValue());
    }
}
