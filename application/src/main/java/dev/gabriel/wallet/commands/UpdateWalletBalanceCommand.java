package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UpdateWalletBalanceCommand implements ICommand {
    private String walletId;
    private BigDecimal balance;
}
