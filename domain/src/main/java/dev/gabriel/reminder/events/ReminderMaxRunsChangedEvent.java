package dev.gabriel.reminder.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ReminderMaxRunsChangedEvent extends ReminderEvent {
    private final Long maxRuns;

    public ReminderMaxRunsChangedEvent(UUID reminderId, Long version, Long maxRuns) {
        super(reminderId, version);
        this.maxRuns = maxRuns;
    }
}
