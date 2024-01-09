package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.SetWalletMainCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetWalletMainCommandHandler implements ICommandHandler<Wallet, SetWalletMainCommand> {
    private final IWalletRepository walletRepository;

    @Autowired
    public SetWalletMainCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet handle(SetWalletMainCommand command) {
        Wallet wallet = walletRepository
                .load(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.setMain(command.getIsMain());

        return walletRepository.registerEvents(wallet);
    }

    @Override
    public Class<SetWalletMainCommand> getCommandType() {
        return SetWalletMainCommand.class;
    }
}
