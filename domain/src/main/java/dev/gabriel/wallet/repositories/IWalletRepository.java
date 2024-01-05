package dev.gabriel.wallet.repositories;

import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.util.Optional;

public interface IWalletRepository extends IDomainRepository {
    Optional<Wallet> findById(WalletId walletId);
    Wallet save(Wallet wallet);
}
