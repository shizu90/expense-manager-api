package dev.gabriel.reminder.repositories;

import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.List;
import java.util.Optional;

public interface IReminderRepository extends IDomainRepository {
    Optional<Reminder> findById(ReminderId reminderId);
    List<Reminder> findByUserId(UserId userId);
    Reminder save(Reminder reminder);
}
