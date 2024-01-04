package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteWalletCommand implements ICommand {
    private String walletId;
}
