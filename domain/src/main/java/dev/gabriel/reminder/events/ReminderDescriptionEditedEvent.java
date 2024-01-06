package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderDescription;
import dev.gabriel.reminder.valueobjects.ReminderId;
import lombok.Getter;

@Getter
public class ReminderDescriptionEditedEvent extends ReminderEvent {
    private final ReminderDescription description;

    public ReminderDescriptionEditedEvent(ReminderId reminderId, Long version, ReminderDescription description) {
        super(reminderId, version);
        this.description = description;
    }
}
