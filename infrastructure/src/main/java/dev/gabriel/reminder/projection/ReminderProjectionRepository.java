package dev.gabriel.reminder.projection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReminderProjectionRepository extends JpaRepository<ReminderProjection, String> {

}
