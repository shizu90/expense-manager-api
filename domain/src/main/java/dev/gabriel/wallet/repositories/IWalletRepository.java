package dev.gabriel.wallet.repositories;

import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.models.Wallet;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.util.List;
import java.util.Optional;

public interface IWalletRepository extends IDomainRepository {
    Optional<Wallet> findById(WalletId walletId);
    List<Wallet> findByUserId(UserId userId);
    List<Wallet> findByUserIdAndIsPrincipal(UserId userId, boolean isPrincipal);
    Wallet save(Wallet wallet);
    void deleteById(WalletId walletId);
}
