package dev.gabriel.reminder.projection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderProjectionRepository extends MongoRepository<ReminderProjection, String> {

}
