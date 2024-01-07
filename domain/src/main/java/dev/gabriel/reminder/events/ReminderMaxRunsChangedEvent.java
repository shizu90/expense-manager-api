package dev.gabriel.reminder.events;

import lombok.Getter;

@Getter
public class ReminderMaxRunsChangedEvent extends ReminderEvent {
    private final Long maxRuns;

    public ReminderMaxRunsChangedEvent(String reminderId, Long version, Long maxRuns) {
        super(reminderId, version);
        this.maxRuns = maxRuns;
    }
}
