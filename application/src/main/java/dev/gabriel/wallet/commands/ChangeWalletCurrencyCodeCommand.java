package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeWalletCurrencyCodeCommand extends Command {
    private String walletId;
    private String currencyCode;
}
