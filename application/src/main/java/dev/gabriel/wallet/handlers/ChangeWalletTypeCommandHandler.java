package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.ChangeWalletTypeCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.models.WalletType;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeWalletTypeCommandHandler implements ICommandHandler<Wallet, ChangeWalletTypeCommand> {
    private final IWalletRepository walletRepository;

    @Autowired
    public ChangeWalletTypeCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet handle(ChangeWalletTypeCommand command) {
        Wallet wallet = walletRepository
                .load(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.changeType(WalletType.valueOf(command.getType()));

        return walletRepository.registerEvents(wallet);
    }

    @Override
    public Class<ChangeWalletTypeCommand> getCommandType() {
        return ChangeWalletTypeCommand.class;
    }
}
