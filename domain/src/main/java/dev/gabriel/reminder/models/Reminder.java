package dev.gabriel.reminder.models;

import dev.gabriel.reminder.events.*;
import dev.gabriel.reminder.exceptions.ReminderAlreadyDeletedException;
import dev.gabriel.reminder.exceptions.ReminderAlreadyStartedException;
import dev.gabriel.reminder.exceptions.ReminderAlreadyStoppedException;
import dev.gabriel.reminder.exceptions.ReminderReachedMaxRunsException;
import dev.gabriel.reminder.valueobjects.*;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
public class Reminder extends AggregateRoot {
    private ReminderName name;
    private ReminderDescription description;
    private ReminderRecurrence recurrence;
    private ReminderRun maxRuns;
    private ReminderRun currentRuns;
    private Boolean isRunning;
    private Instant lastRun;
    private UserId userId;
    private Boolean isDeleted;

    private Reminder(String id, String name, String description, Long recurrence, Long maxRuns, UserId userId) {
        super(ReminderId.create(id));
        raiseEvent(new ReminderCreatedEvent(
                ReminderId.create(id),
                getNextVersion(),
                ReminderName.create(name),
                ReminderDescription.create(description),
                ReminderRecurrence.create(recurrence),
                ReminderRun.create(maxRuns),
                ReminderRun.create(1L),
                userId
        ));
    }

    public static Reminder create(String id, String name, String description, Long recurrence, Long maxRuns, UserId userId) {
        return new Reminder(id, name, description, recurrence, maxRuns, userId);
    }

    public void rename(String name) {
        raiseEvent(new ReminderRenamedEvent(getId(), getNextVersion(), ReminderName.create(name)));
    }

    public void editDescription(String description) {
        raiseEvent(new ReminderDescriptionEditedEvent(getId(), getNextVersion(), ReminderDescription.create(description)));
    }

    public void changeRecurrence(Long recurrence) {
        raiseEvent(new ReminderRecurrenceChangedEvent(getId(), getNextVersion(), ReminderRecurrence.create(recurrence)));
    }

    public void changeMaxRuns(Long maxRuns) {
        raiseEvent(new ReminderMaxRunsChangedEvent(getId(), getNextVersion(), ReminderRun.create(maxRuns)));
    }

    public LocalDate getNextReminder() {
        return LocalDate.from(lastRun).plusDays(recurrence.getValue());
    }

    public void start() {
        if(isRunning) throw new ReminderAlreadyStartedException();

        raiseEvent(new ReminderStartedEvent(getId(), getNextVersion()));
    }

    public void run() {
        if(currentRuns.equals(maxRuns)) throw new ReminderReachedMaxRunsException();

        raiseEvent(new ReminderRanEvent(getId(), getNextVersion()));

        if(currentRuns.increment(1L).equals(maxRuns)) stop();
    }

    public void stop() {
        if(!isRunning) throw new ReminderAlreadyStoppedException();

        raiseEvent(new ReminderStoppedEvent(getId(), getNextVersion()));
    }

    public void restart() {
        raiseEvent(new ReminderRestartedEvent(getId(), getNextVersion()));
        start();
    }

    public void delete() {
        if(isDeleted) {
            throw new ReminderAlreadyDeletedException();
        }else {
            raiseEvent(new ReminderDeletedEvent(getId(), getNextVersion()));
        }
    }

    @SuppressWarnings("unused")
    private void apply(ReminderCreatedEvent event) {
        this.name = event.getName();
        this.description = event.getDescription();
        this.maxRuns = event.getMaxRuns();
        this.recurrence = event.getRecurrence();
        this.userId = event.getUserId();
        this.currentRuns = event.getCurrentRuns();
        this.isDeleted = false;
        this.lastRun = Instant.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRenamedEvent event) {
        this.name = event.getName();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderDescriptionEditedEvent event) {
        this.description = event.getDescription();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRecurrenceChangedEvent event) {
        this.recurrence = event.getRecurrence();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderMaxRunsChangedEvent event) {
        this.maxRuns = event.getMaxRuns();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderStartedEvent event) {
        this.isRunning = true;
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRanEvent event) {
        this.currentRuns = currentRuns.increment(1L);
    }

    @SuppressWarnings("unused")
    private void apply(ReminderStoppedEvent event) {
        this.isRunning = false;
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRestartedEvent event) {
        this.currentRuns = ReminderRun.create(1L);
    }

    @SuppressWarnings("unused")
    private void apply(ReminderDeletedEvent event) {
        this.isRunning = true;
    }

    @Override
    public ReminderId getId() {
        return (ReminderId) id;
    }
}
