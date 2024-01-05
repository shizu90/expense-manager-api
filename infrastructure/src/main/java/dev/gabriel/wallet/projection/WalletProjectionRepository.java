package dev.gabriel.wallet.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletProjectionRepository extends JpaRepository<WalletProjection, String> {

}
