package dev.gabriel.reminder.repositories;

import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.Optional;

public interface IReminderRepository extends IDomainRepository {
    Optional<Reminder> findById(ReminderId reminderId);
    Reminder save(Reminder reminder);
}
