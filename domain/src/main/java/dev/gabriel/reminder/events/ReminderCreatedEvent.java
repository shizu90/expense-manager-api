package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.*;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

@Getter
public class ReminderCreatedEvent extends ReminderEvent {
    private final ReminderName name;
    private final ReminderDescription description;
    private final ReminderRecurrence recurrence;
    private final ReminderRun maxRuns;
    private final ReminderRun currentRuns;
    private final UserId userId;

    public ReminderCreatedEvent(ReminderId reminderId,
                                Long version,
                                ReminderName name,
                                ReminderDescription description,
                                ReminderRecurrence recurrence,
                                ReminderRun maxRuns,
                                ReminderRun currentRuns,
                                UserId userId
    ) {
        super(reminderId, version);
        this.name = name;
        this.description = description;
        this.recurrence = recurrence;
        this.maxRuns = maxRuns;
        this.currentRuns = currentRuns;
        this.userId = userId;
    }
}
