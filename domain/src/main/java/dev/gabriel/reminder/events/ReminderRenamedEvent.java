package dev.gabriel.reminder.events;

import lombok.Getter;

@Getter
public class ReminderRenamedEvent extends ReminderEvent {
    private final String name;

    public ReminderRenamedEvent(String reminderId, Long version, String name) {
        super(reminderId, version);
        this.name = name;
    }
}
