package dev.gabriel.reminder.events;

public class ReminderStartedEvent extends ReminderEvent {
    public ReminderStartedEvent(String reminderId, Long version) {
        super(reminderId, version);
    }
}
