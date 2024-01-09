package dev.gabriel.wallet.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletProjectionRepository extends MongoRepository<WalletProjection, String> {

}
