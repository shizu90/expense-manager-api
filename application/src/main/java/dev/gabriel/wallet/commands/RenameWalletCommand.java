package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

@Getter
public class RenameWalletCommand implements ICommand {
    private String walletId;
    private String name;
}
