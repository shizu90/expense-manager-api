package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.commands.ChangeWalletCurrencyCodeCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

public class ChangeWalletCurrencyCodeCommandHandler implements ICommandHandler<Wallet, ChangeWalletCurrencyCodeCommand> {
    private final IWalletRepository walletRepository;

    public ChangeWalletCurrencyCodeCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet execute(ChangeWalletCurrencyCodeCommand command) {
        Wallet wallet = walletRepository.findById(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.changeCurrencyCode(CurrencyCode.valueOf(command.getCurrencyCode()));

        return walletRepository.save(wallet);
    }
}