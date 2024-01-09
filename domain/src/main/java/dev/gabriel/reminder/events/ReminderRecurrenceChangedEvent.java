package dev.gabriel.reminder.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReminderRecurrenceChangedEvent extends ReminderEvent {
    private final Long recurrence;

    public ReminderRecurrenceChangedEvent(UUID reminderId, Long version, Long recurrence) {
        super(reminderId, version);
        this.recurrence = recurrence;
    }
}
