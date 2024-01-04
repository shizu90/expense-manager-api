package dev.gabriel.reminder.repositories;

import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.shared.repositories.IDomainRepository;

public interface IReminderRepository extends IDomainRepository {
    Reminder save(Reminder reminder);
}
