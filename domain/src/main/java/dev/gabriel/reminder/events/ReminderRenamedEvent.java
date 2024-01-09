package dev.gabriel.reminder.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReminderRenamedEvent extends ReminderEvent {
    private final String name;

    public ReminderRenamedEvent(UUID reminderId, Long version, String name) {
        super(reminderId, version);
        this.name = name;
    }
}
