package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.SetWalletMainCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

public class SetWalletMainCommandHandler implements ICommandHandler<Wallet, SetWalletMainCommand> {
    private final IWalletRepository walletRepository;

    public SetWalletMainCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet execute(SetWalletMainCommand command) {
        Wallet wallet = walletRepository.findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.setMain(command.getIsMain());

        return walletRepository.save(wallet);
    }
}
