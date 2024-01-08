package dev.gabriel.wallet.handlers;

import dev.gabriel.shared.handlers.ICommandHandler;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.exceptions.UserNotFoundException;
import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.commands.CreateWalletCommand;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.models.WalletType;
import dev.gabriel.wallet.repositories.IWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class CreateWalletCommandHandler implements ICommandHandler<Wallet, CreateWalletCommand> {
    private final IWalletRepository walletRepository;
    private final IUserRepository userRepository;

    @Autowired
    public CreateWalletCommandHandler(IWalletRepository walletRepository, IUserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Wallet handle(CreateWalletCommand command) {
        User user = userRepository.findById(UserId.create(command.getUserId())).orElseThrow(() -> new UserNotFoundException(command.getUserId()));
        Wallet wallet = Wallet.create(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getDescription(),
                command.getAmount(),
                CurrencyCode.valueOf(command.getCurrencyCode()),
                command.getIsMain(),
                WalletType.valueOf(command.getType()),
                user.getId()
        );

        return walletRepository.save(wallet);
    }
}
