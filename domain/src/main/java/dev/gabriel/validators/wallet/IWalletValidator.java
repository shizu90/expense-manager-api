package dev.gabriel.validators.wallet;

import dev.gabriel.entities.wallet.Wallet;

import java.util.List;

public interface IWalletValidator {
    String validateName(String name);
    String validateComment(String comment);
    List<String> validate(Wallet wallet);
}
