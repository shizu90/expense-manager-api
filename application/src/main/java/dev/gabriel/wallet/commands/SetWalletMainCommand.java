package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SetWalletMainCommand extends Command {
    private UUID walletId;
    private Boolean isMain;
}
