package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.UpdateWalletBalanceCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

public class UpdateWalletBalanceCommandHandler implements ICommandHandler<Wallet, UpdateWalletBalanceCommand> {
    private final IWalletRepository walletRepository;

    public UpdateWalletBalanceCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet execute(UpdateWalletBalanceCommand command) {
        Wallet wallet = walletRepository.findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.updateBalance(command.getBalance());

        return walletRepository.save(wallet);
    }
}