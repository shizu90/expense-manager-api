package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.DeleteWalletCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

public class DeleteWalletCommandHandler implements ICommandHandler<Wallet, DeleteWalletCommand> {
    private final IWalletRepository walletRepository;

    public DeleteWalletCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet execute(DeleteWalletCommand command) {
        Wallet wallet = walletRepository.findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.delete();

        walletRepository.save(wallet);

        return null;
    }
}
