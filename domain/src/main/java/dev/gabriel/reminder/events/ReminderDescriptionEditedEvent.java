package dev.gabriel.reminder.events;

import lombok.Getter;

@Getter
public class ReminderDescriptionEditedEvent extends ReminderEvent {
    private final String description;

    public ReminderDescriptionEditedEvent(String reminderId, Long version, String description) {
        super(reminderId, version);
        this.description = description;
    }
}
