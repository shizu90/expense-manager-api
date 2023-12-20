package dev.gabriel.wallet.validation;

import dev.gabriel.wallet.entities.Wallet;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

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
