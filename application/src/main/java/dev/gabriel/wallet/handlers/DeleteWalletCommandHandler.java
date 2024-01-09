package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.DeleteWalletCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteWalletCommandHandler implements ICommandHandler<Wallet, DeleteWalletCommand> {
    private final IWalletRepository walletRepository;

    @Autowired
    public DeleteWalletCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet handle(DeleteWalletCommand command) {
        Wallet wallet = walletRepository
                .load(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.delete();

        walletRepository.registerEvents(wallet);

        return null;
    }

    @Override
    public Class<DeleteWalletCommand> getCommandType() {
        return DeleteWalletCommand.class;
    }
}
