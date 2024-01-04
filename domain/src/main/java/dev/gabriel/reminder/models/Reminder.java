package dev.gabriel.reminder.models;

import dev.gabriel.reminder.events.*;
import dev.gabriel.reminder.exceptions.ReminderAlreadyDeletedException;
import dev.gabriel.reminder.exceptions.ReminderAlreadyStartedException;
import dev.gabriel.reminder.exceptions.ReminderAlreadyStoppedException;
import dev.gabriel.reminder.valueobjects.ReminderDescription;
import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.reminder.valueobjects.ReminderName;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
public class Reminder extends AggregateRoot {
    private ReminderName name;
    private ReminderDescription description;
    private Long recurrence;
    private Long maxRuns;
    private Long currentRuns;
    private Boolean isRunning;
    private Instant lastRun;
    private UserId userId;

    private Reminder(String id, String name, String description, Long recurrence, Long maxRuns, UserId userId) {
        super(ReminderId.create(id));
        this.name = ReminderName.create(name);
        this.description = ReminderDescription.create(description);
        this.recurrence = recurrence;
        this.maxRuns = maxRuns;
        this.currentRuns = 0L;
        this.lastRun = createdAt;
        this.isRunning = false;
        this.userId = userId;
    }

    public static Reminder create(String id, String name, String description, Long recurrence, Long maxRuns, UserId userId) {
        Reminder reminder = new Reminder(id, name, description, recurrence, maxRuns, userId);
        reminder.raiseEvent(new ReminderCreatedEvent(reminder.getId()));
        return reminder;
    }

    public void rename(String name) {
        this.name = ReminderName.create(name);
        updatedAt = Instant.now();
        raiseEvent(new ReminderRenamedEvent(getId()));
    }

    public void editDescription(String description) {
        this.description = ReminderDescription.create(description);
        updatedAt = Instant.now();
        raiseEvent(new ReminderDescriptionEditedEvent(getId()));
    }

    public void changeRecurrence(Long recurrence) {
        this.recurrence = recurrence;
        updatedAt = Instant.now();
        raiseEvent(new ReminderRecurrenceChangedEvent(getId()));
    }

    public void changeMaxRuns(Long maxRuns) {
        this.maxRuns = maxRuns;
        updatedAt = Instant.now();
        raiseEvent(new ReminderMaxRunsChangedEvent(getId()));
    }

    public LocalDate getNextReminder() {
        return LocalDate.from(lastRun).plusDays(recurrence);
    }

    public void start() {
        if(isRunning) throw new ReminderAlreadyStartedException();

        this.isRunning = true;
        updatedAt = Instant.now();
        raiseEvent(new ReminderStartedEvent(getId()));
    }

    public void stop() {
        if(!isRunning) throw new ReminderAlreadyStoppedException();

        this.isRunning = false;
        updatedAt = Instant.now();
        raiseEvent(new ReminderStoppedEvent(getId()));
    }

    public void restart() {
        this.currentRuns = 0L;
        start();
    }

    public void delete() {
        if(isDeleted) {
            throw new ReminderAlreadyDeletedException();
        }else {
            isDeleted = true;
            raiseEvent(new ReminderDeletedEvent(getId()));
        }
    }

    @Override
    public ReminderId getId() {
        return (ReminderId) id;
    }
}
