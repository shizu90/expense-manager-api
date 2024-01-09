package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UpdateWalletBalanceCommand extends Command {
    private UUID walletId;
    private BigDecimal balance;
}
