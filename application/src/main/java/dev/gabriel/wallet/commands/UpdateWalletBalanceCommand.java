package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateWalletBalanceCommand implements ICommand {
    private String walletId;
    private BigDecimal balance;
}
