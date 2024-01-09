package dev.gabriel.category.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProjectionRepository extends MongoRepository<CategoryProjection, String> {

}
