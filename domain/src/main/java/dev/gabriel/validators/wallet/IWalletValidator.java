package dev.gabriel.validators.wallet;

import dev.gabriel.entities.wallet.Wallet;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;

import java.time.LocalDate;
import java.util.List;

public interface IWalletValidator {
    String validateName(String name);
    String validateComment(String comment);
    String validateUserId(Identity userId);
    String validateLastBalanceUpdate(LocalDate lastBalanceUpdate);
    String validateInitialBalance(Money initialBalance);
    List<String> validate(Wallet wallet);
}
