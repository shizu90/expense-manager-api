package dev.gabriel.budget.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetProjectionRepository extends MongoRepository<BudgetProjection, String> {
}
