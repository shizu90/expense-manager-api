package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.reminder.valueobjects.ReminderRun;
import lombok.Getter;

@Getter
public class ReminderMaxRunsChangedEvent extends ReminderEvent {
    private final ReminderRun maxRuns;

    public ReminderMaxRunsChangedEvent(ReminderId reminderId, Long version, ReminderRun maxRuns) {
        super(reminderId, version);
        this.maxRuns = maxRuns;
    }
}
