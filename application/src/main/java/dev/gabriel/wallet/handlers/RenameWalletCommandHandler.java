package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.RenameWalletCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RenameWalletCommandHandler implements ICommandHandler<Wallet, RenameWalletCommand> {
    private final IWalletRepository walletRepository;

    @Autowired
    public RenameWalletCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet handle(RenameWalletCommand command) {
        Wallet wallet = walletRepository.findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.rename(command.getName());

        return walletRepository.save(wallet);
    }

    @Override
    public Class<RenameWalletCommand> getCommandType() {
        return RenameWalletCommand.class;
    }
}
