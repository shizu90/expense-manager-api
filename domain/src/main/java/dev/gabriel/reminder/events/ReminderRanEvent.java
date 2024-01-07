package dev.gabriel.reminder.events;

public class ReminderRanEvent extends ReminderEvent {
    public ReminderRanEvent(String reminderId, Long version) {
        super(reminderId, version);
    }
}
