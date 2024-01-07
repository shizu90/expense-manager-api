package dev.gabriel.reminder.events;

public class ReminderRestartedEvent extends ReminderEvent {
    public ReminderRestartedEvent(String reminderId, Long version) {
        super(reminderId, version);
    }
}
