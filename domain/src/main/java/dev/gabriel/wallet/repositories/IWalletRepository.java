package dev.gabriel.wallet.repositories;

import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.wallet.models.Wallet;

import java.util.List;
import java.util.Optional;

public interface IWalletRepository extends IDomainRepository {
    Optional<Wallet> findById(String id);
    List<Wallet> findByUserId(String userId);
    List<Wallet> findByUserIdAndIsPrincipal(String userId, boolean isPrincipal);
    Wallet save(Wallet wallet);
    void deleteById(String id);
}
