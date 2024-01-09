package dev.gabriel.bill.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillProjectionRepository extends MongoRepository<BillProjection, String> {

}
