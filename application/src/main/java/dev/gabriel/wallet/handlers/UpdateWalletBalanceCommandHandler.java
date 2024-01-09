package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.UpdateWalletBalanceCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateWalletBalanceCommandHandler implements ICommandHandler<Wallet, UpdateWalletBalanceCommand> {
    private final IWalletRepository walletRepository;

    @Autowired
    public UpdateWalletBalanceCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet handle(UpdateWalletBalanceCommand command) {
        Wallet wallet = walletRepository
                .load(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.updateBalance(command.getBalance());

        return walletRepository.registerEvents(wallet);
    }

    @Override
    public Class<UpdateWalletBalanceCommand> getCommandType() {
        return UpdateWalletBalanceCommand.class;
    }
}
