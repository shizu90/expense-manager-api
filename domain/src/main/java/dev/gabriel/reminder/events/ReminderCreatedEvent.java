package dev.gabriel.reminder.events;

import lombok.Getter;

@Getter
public class ReminderCreatedEvent extends ReminderEvent {
    private final String name;
    private final String description;
    private final Long recurrence;
    private final Long maxRuns;
    private final String userId;

    public ReminderCreatedEvent(String reminderId,
                                Long version,
                                String name,
                                String description,
                                Long recurrence,
                                Long maxRuns,
                                String userId
    ) {
        super(reminderId, version);
        this.name = name;
        this.description = description;
        this.recurrence = recurrence;
        this.maxRuns = maxRuns;
        this.userId = userId;
    }
}
