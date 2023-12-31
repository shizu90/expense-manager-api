package dev.gabriel.wallet.eventstore;

import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.repositories.IWalletRepository;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.util.Optional;

public class WalletEventStore implements IWalletRepository {
    @Override
    public Optional<Wallet> load(WalletId walletId) {
        return Optional.empty();
    }

    @Override
    public Wallet registerEvents(Wallet wallet) {
        return null;
    }
}
