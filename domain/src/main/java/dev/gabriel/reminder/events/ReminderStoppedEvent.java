package dev.gabriel.reminder.events;

public class ReminderStoppedEvent extends ReminderEvent {
    public ReminderStoppedEvent(String reminderId, Long version) {
        super(reminderId, version);
    }
}
