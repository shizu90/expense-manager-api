package dev.gabriel.reminder.eventstore;

import dev.gabriel.reminder.models.Reminder;
import dev.gabriel.reminder.repositories.IReminderRepository;
import dev.gabriel.reminder.valueobjects.ReminderId;

import java.util.Optional;

public class ReminderEventStore implements IReminderRepository {
    @Override
    public Optional<Reminder> findById(ReminderId reminderId) {
        return Optional.empty();
    }

    @Override
    public Reminder save(Reminder reminder) {
        return null;
    }
}
