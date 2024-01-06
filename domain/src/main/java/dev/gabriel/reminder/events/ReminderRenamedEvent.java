package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.reminder.valueobjects.ReminderName;
import lombok.Getter;

@Getter
public class ReminderRenamedEvent extends ReminderEvent {
    private final ReminderName name;

    public ReminderRenamedEvent(ReminderId reminderId, Long version, ReminderName name) {
        super(reminderId, version);
        this.name = name;
    }
}
