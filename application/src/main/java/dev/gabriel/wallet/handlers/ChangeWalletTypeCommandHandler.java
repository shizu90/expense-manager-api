package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.ChangeWalletTypeCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.models.WalletType;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

public class ChangeWalletTypeCommandHandler implements ICommandHandler<Wallet, ChangeWalletTypeCommand> {
    private final IWalletRepository walletRepository;

    public ChangeWalletTypeCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet execute(ChangeWalletTypeCommand command) {
        Wallet wallet = walletRepository.findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.changeType(WalletType.valueOf(command.getType()));

        return walletRepository.save(wallet);
    }
}