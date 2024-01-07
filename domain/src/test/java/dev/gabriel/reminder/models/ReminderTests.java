package dev.gabriel.reminder.models;

import dev.gabriel.reminder.events.*;
import dev.gabriel.reminder.exceptions.*;
import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ReminderTests {
    Reminder populate() {
        return Reminder.create(
                UUID.randomUUID().toString(),
                "Name",
                "Description",
                2L,
                20L,
                UserId.create(UUID.randomUUID().toString())
        );
    }

    @Test
    @DisplayName("Create reminder")
    void createReminder() {
        Reminder reminder = populate();

        Assertions.assertInstanceOf(ReminderCreatedEvent.class, reminder.getEvents().get(0));
        Assertions.assertEquals("Name", reminder.getName().getValue());
    }

    @Test
    @DisplayName("Create reminder from event stream")
    void createReminderFromEventStream() {
        Reminder reminder = populate();
        List<DomainEvent> events = Arrays.asList(new DomainEvent[] {
                new ReminderCreatedEvent(
                        reminder.getId().getValue(),
                        reminder.getBaseVersion(),
                        reminder.getName().getValue(),
                        reminder.getDescription().getValue(),
                        reminder.getRecurrence().getValue(),
                        reminder.getMaxRuns().getValue(),
                        reminder.getUserId().getValue()
                )
        });
        Reminder reminderFromEventStream = Reminder.create(reminder.getId().getValue(), events);

        Assertions.assertEquals(reminder.getId(), reminderFromEventStream.getId());
    }

    @Test
    @DisplayName("Rename reminder")
    void renameReminder() {
        Reminder reminder = populate();
        reminder.rename("NewName");

        Assertions.assertInstanceOf(ReminderRenamedEvent.class, reminder.getEvents().get(1));
        Assertions.assertEquals("NewName", reminder.getName().getValue());
    }

    @Test
    @DisplayName("Rename reminder should throw ReminderValidationException")
    void renameReminderShouldThrowReminderValidationException() {
        Reminder reminder = populate();

        Assertions.assertThrows(ReminderValidationException.class, () -> {
            reminder.rename(null);
        });
        Assertions.assertEquals(1, reminder.getEvents().size());
    }

    @Test
    @DisplayName("Edit description")
    void editDescription() {
        Reminder reminder = populate();
        reminder.editDescription("NewDescription");

        Assertions.assertInstanceOf(ReminderDescriptionEditedEvent.class, reminder.getEvents().get(1));
        Assertions.assertEquals("NewDescription", reminder.getDescription().getValue());
    }

    @Test
    @DisplayName("Edit description should throw ReminderValidationException")
    void editDescriptionShouldThrowReminderValidationException() {
        Reminder reminder = populate();

        Assertions.assertThrows(ReminderValidationException.class, () -> {
            reminder.editDescription(null);
        });
        Assertions.assertEquals(1, reminder.getEvents().size());
    }

    @Test
    @DisplayName("Change recurrence")
    void changeRecurrence() {
        Reminder reminder = populate();
        reminder.changeRecurrence(4L);

        Assertions.assertInstanceOf(ReminderRecurrenceChangedEvent.class, reminder.getEvents().get(1));
        Assertions.assertEquals(4L, reminder.getRecurrence().getValue());
    }

    @Test
    @DisplayName("Change recurrence should throw ReminderValidationException")
    void changeRecurrenceShouldThrowReminderValidationException() {
        Reminder reminder = populate();

        Assertions.assertThrows(ReminderValidationException.class, () -> {
            reminder.changeRecurrence(null);
        });
        Assertions.assertEquals(1, reminder.getEvents().size());
    }

    @Test
    @DisplayName("Change max runs")
    void changeMaxRuns() {
        Reminder reminder = populate();
        reminder.changeMaxRuns(40L);

        Assertions.assertInstanceOf(ReminderMaxRunsChangedEvent.class, reminder.getEvents().get(1));
        Assertions.assertEquals(40L, reminder.getMaxRuns().getValue());
    }

    @Test
    @DisplayName("Change max runs should throw ReminderValidationException")
    void changeMaxRunsShouldThrowReminderValidationException() {
        Reminder reminder = populate();

        Assertions.assertThrows(ReminderValidationException.class, () -> {
            reminder.changeMaxRuns(null);
        });
        Assertions.assertEquals(1, reminder.getEvents().size());
    }

    @Test
    @DisplayName("Get next reminder")
    void getNextReminder() {
        Reminder reminder = populate();
        LocalDate nextReminder = reminder.getNextReminder();

        Assertions.assertEquals(LocalDate.now().plusDays(2), nextReminder);
    }

    @Test
    @DisplayName("Start reminder")
    void startReminder() {
        Reminder reminder = populate();
        reminder.start();

        Assertions.assertInstanceOf(ReminderStartedEvent.class, reminder.getEvents().get(1));
        Assertions.assertTrue(reminder.getStarted());
    }

    @Test
    @DisplayName("Start reminder should throw ReminderAlreadyStartedException")
    void startReminderShouldThrowReminderAlreadyStartedException() {
        Reminder reminder = populate();
        reminder.start();

        Assertions.assertInstanceOf(ReminderStartedEvent.class, reminder.getEvents().get(1));
        Assertions.assertThrows(ReminderAlreadyStartedException.class, reminder::start);
        Assertions.assertEquals(2, reminder.getEvents().size());
    }

    @Test
    @DisplayName("Stop reminder")
    void stopReminder() {
        Reminder reminder = populate();
        reminder.start();
        reminder.stop();

        Assertions.assertInstanceOf(ReminderStartedEvent.class, reminder.getEvents().get(1));
        Assertions.assertInstanceOf(ReminderStoppedEvent.class, reminder.getEvents().get(2));
        Assertions.assertFalse(reminder.getStarted());
    }

    @Test
    @DisplayName("Stop reminder should throw ReminderAlreadyStoppedEvent")
    void stopReminderShouldThrowReminderAlreadyStoppedEvent() {
        Reminder reminder = populate();
        reminder.start();
        reminder.stop();

        Assertions.assertInstanceOf(ReminderStartedEvent.class, reminder.getEvents().get(1));
        Assertions.assertInstanceOf(ReminderStoppedEvent.class, reminder.getEvents().get(2));
        Assertions.assertThrows(ReminderAlreadyStoppedException.class, reminder::stop);
        Assertions.assertEquals(3, reminder.getEvents().size());
    }

    @Test
    @DisplayName("Run reminder")
    void runReminder() {
        Reminder reminder = populate();
        reminder.start();
        reminder.run();

        Assertions.assertInstanceOf(ReminderStartedEvent.class, reminder.getEvents().get(1));
        Assertions.assertInstanceOf(ReminderRanEvent.class, reminder.getEvents().get(2));
        Assertions.assertEquals(2L, reminder.getCurrentRuns().getValue());
    }

    @Test
    @DisplayName("Run reminder should throw ReminderNotStartedException")
    void runReminderShouldThrowReminderNotStartedException() {
        Reminder reminder = populate();

        Assertions.assertThrows(ReminderNotStartedException.class, reminder::run);
        Assertions.assertEquals(1, reminder.getEvents().size());
    }

    @Test
    @DisplayName("Restart reminder")
    void restartReminder() {
        Reminder reminder = populate();
        reminder.restart();

        Assertions.assertInstanceOf(ReminderRestartedEvent.class, reminder.getEvents().get(1));
        Assertions.assertEquals(1L, reminder.getCurrentRuns().getValue());
    }

    @Test
    @DisplayName("Delete reminder")
    void deleteReminder() {
        Reminder reminder = populate();
        reminder.delete();

        Assertions.assertInstanceOf(ReminderDeletedEvent.class, reminder.getEvents().get(1));
        Assertions.assertTrue(reminder.getIsDeleted());
    }

    @Test
    @DisplayName("Delete reminder should throw ReminderAlreadyDeletedException")
    void deleteReminderShouldThrowReminderAlreadyDeletedException() {
        Reminder reminder = populate();
        reminder.delete();

        Assertions.assertInstanceOf(ReminderDeletedEvent.class, reminder.getEvents().get(1));
        Assertions.assertThrows(ReminderAlreadyDeletedException.class, reminder::delete);
        Assertions.assertEquals(2, reminder.getEvents().size());
    }
}
