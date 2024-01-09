package dev.gabriel.reminder.models;

import dev.gabriel.reminder.events.*;
import dev.gabriel.reminder.exceptions.*;
import dev.gabriel.reminder.valueobjects.*;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
public class Reminder extends AggregateRoot {
    private ReminderName name;
    private ReminderDescription description;
    private ReminderRecurrence recurrence;
    private ReminderRun maxRuns;
    private ReminderRun currentRuns;
    private Boolean started;
    private LocalDate lastRun;
    private UserId userId;
    private Boolean isDeleted;

    private Reminder(UUID id, String name, String description, Long recurrence, Long maxRuns, UserId userId) {
        super(ReminderId.create(id));
        ReminderName.validate(name);
        ReminderDescription.validate(description);
        ReminderRecurrence.validate(recurrence, maxRuns);
        ReminderRun.validate(maxRuns, recurrence);
        raiseEvent(new ReminderCreatedEvent(
                id,
                getNextVersion(),
                name,
                description,
                recurrence,
                maxRuns,
                userId.getValue()
        ));
    }

    private Reminder(UUID id, List<DomainEvent> events) {
        super(ReminderId.create(id), events);
    }

    public static Reminder create(UUID id, String name, String description, Long recurrence, Long maxRuns, UserId userId) {
        return new Reminder(id, name, description, recurrence, maxRuns, userId);
    }

    public static Reminder create(UUID id, List<DomainEvent> events) {
        return new Reminder(id, events);
    }

    public void rename(String name) {
        ReminderName.validate(name);
        raiseEvent(new ReminderRenamedEvent(getId().getValue(), getNextVersion(), name));
    }

    public void editDescription(String description) {
        ReminderDescription.validate(description);
        raiseEvent(new ReminderDescriptionEditedEvent(getId().getValue(), getNextVersion(), description));
    }

    public void changeRecurrence(Long recurrence) {
        ReminderRecurrence.validate(recurrence, maxRuns.getValue());
        raiseEvent(new ReminderRecurrenceChangedEvent(getId().getValue(), getNextVersion(), recurrence));
    }

    public void changeMaxRuns(Long maxRuns) {
        ReminderRun.validate(maxRuns, recurrence.getValue());
        raiseEvent(new ReminderMaxRunsChangedEvent(getId().getValue(), getNextVersion(), maxRuns));
    }

    public LocalDate getNextReminder() {
        return lastRun.plusDays(recurrence.getValue());
    }

    public void start() {
        if(started) throw new ReminderAlreadyStartedException();

        raiseEvent(new ReminderStartedEvent(getId().getValue(), getNextVersion()));
    }

    public void run() {
        if(!started) throw new ReminderNotStartedException();
        if(currentRuns.equals(maxRuns)) throw new ReminderReachedMaxRunsException();

        raiseEvent(new ReminderRanEvent(getId().getValue(), getNextVersion()));

        if(currentRuns.equals(maxRuns)) stop();
    }

    public void stop() {
        if(!started) throw new ReminderAlreadyStoppedException();

        raiseEvent(new ReminderStoppedEvent(getId().getValue(), getNextVersion()));
    }

    public void restart() {
        raiseEvent(new ReminderRestartedEvent(getId().getValue(), getNextVersion()));
        start();
    }

    public void delete() {
        if(isDeleted) {
            throw new ReminderAlreadyDeletedException();
        }else {
            raiseEvent(new ReminderDeletedEvent(getId().getValue(), getNextVersion()));
        }
    }

    @SuppressWarnings("unused")
    private void apply(ReminderCreatedEvent event) {
        this.name = ReminderName.create(event.getName());
        this.description = ReminderDescription.create(event.getDescription());
        this.maxRuns = ReminderRun.create(event.getMaxRuns());
        this.recurrence = ReminderRecurrence.create(event.getRecurrence());
        this.userId = UserId.create(event.getUserId());
        this.currentRuns = ReminderRun.create(1L);
        this.started = false;
        this.isDeleted = false;
        this.lastRun = LocalDate.now();
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRenamedEvent event) {
        this.name = ReminderName.create(event.getName());
    }

    @SuppressWarnings("unused")
    private void apply(ReminderDescriptionEditedEvent event) {
        this.description = ReminderDescription.create(event.getDescription());
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRecurrenceChangedEvent event) {
        this.recurrence = ReminderRecurrence.create(event.getRecurrence());
    }

    @SuppressWarnings("unused")
    private void apply(ReminderMaxRunsChangedEvent event) {
        this.maxRuns = ReminderRun.create(event.getMaxRuns());
    }

    @SuppressWarnings("unused")
    private void apply(ReminderStartedEvent event) {
        this.started = true;
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRanEvent event) {
        this.currentRuns = currentRuns.increment(1L);
    }

    @SuppressWarnings("unused")
    private void apply(ReminderStoppedEvent event) {
        this.started = false;
    }

    @SuppressWarnings("unused")
    private void apply(ReminderRestartedEvent event) {
        this.currentRuns = ReminderRun.create(1L);
    }

    @SuppressWarnings("unused")
    private void apply(ReminderDeletedEvent event) {
        this.isDeleted = true;
    }

    @Override
    public ReminderId getId() {
        return (ReminderId) id;
    }
}
