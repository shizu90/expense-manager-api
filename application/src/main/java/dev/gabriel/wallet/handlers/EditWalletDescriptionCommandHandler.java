package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.wallet.commands.EditWalletDescriptionCommand;
import dev.gabriel.wallet.exceptions.WalletNotFoundException;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditWalletDescriptionCommandHandler implements ICommandHandler<Wallet, EditWalletDescriptionCommand> {
    private final IWalletRepository walletRepository;

    @Autowired
    public EditWalletDescriptionCommandHandler(IWalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet handle(EditWalletDescriptionCommand command) {
        Wallet wallet = walletRepository
                .load(WalletId.create(command.getWalletId())).orElseThrow(() -> new WalletNotFoundException(command.getWalletId()));
        wallet.editDescription(command.getDescription());

        return walletRepository.registerEvents(wallet);
    }

    @Override
    public Class<EditWalletDescriptionCommand> getCommandType() {
        return EditWalletDescriptionCommand.class;
    }
}
