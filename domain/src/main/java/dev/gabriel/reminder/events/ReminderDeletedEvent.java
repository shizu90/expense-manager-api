package dev.gabriel.reminder.events;

public class ReminderDeletedEvent extends ReminderEvent {
    public ReminderDeletedEvent(String reminderId, Long version) {
        super(reminderId, version);
    }
}
