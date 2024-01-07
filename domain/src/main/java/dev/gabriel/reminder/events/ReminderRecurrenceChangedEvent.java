package dev.gabriel.reminder.events;

import lombok.Getter;

@Getter
public class ReminderRecurrenceChangedEvent extends ReminderEvent {
    private final Long recurrence;

    public ReminderRecurrenceChangedEvent(String reminderId, Long version, Long recurrence) {
        super(reminderId, version);
        this.recurrence = recurrence;
    }
}
