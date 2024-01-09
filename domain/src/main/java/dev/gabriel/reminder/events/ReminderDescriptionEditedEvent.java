package dev.gabriel.reminder.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReminderDescriptionEditedEvent extends ReminderEvent {
    private final String description;

    public ReminderDescriptionEditedEvent(UUID reminderId, Long version, String description) {
        super(reminderId, version);
        this.description = description;
    }
}
