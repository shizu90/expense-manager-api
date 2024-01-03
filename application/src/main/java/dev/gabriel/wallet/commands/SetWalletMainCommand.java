package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

@Getter
public class SetWalletMainCommand implements ICommand {
    private String walletId;
    private Boolean isMain;
}
