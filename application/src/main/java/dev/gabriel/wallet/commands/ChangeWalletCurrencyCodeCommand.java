package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeWalletCurrencyCodeCommand implements ICommand {
    private String walletId;
    private String currencyCode;
}
