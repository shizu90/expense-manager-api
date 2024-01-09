package dev.gabriel.user.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProjectionRepository extends MongoRepository<UserProjection, String> {

}
