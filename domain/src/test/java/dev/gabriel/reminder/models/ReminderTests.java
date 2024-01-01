package dev.gabriel.reminder.models;

import dev.gabriel.reminder.events.*;
import dev.gabriel.reminder.exceptions.ReminderAlreadyDeletedException;
import dev.gabriel.reminder.exceptions.ReminderAlreadyStartedException;
import dev.gabriel.reminder.exceptions.ReminderAlreadyStoppedException;
import dev.gabriel.reminder.exceptions.ReminderValidationException;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ReminderTests {
    Reminder populate() {
        return Reminder.create(
                UUID.randomUUID().toString(),
                "Name",
                "Comment",
                4L,
                12L,
                UserId.create(UUID.randomUUID().toString())
        );
    }

    @Test
    @DisplayName("Create reminder test case: success")
    void createReminderTestCaseSuccess() {
        Reminder reminder = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, reminder);
        Assertions.assertInstanceOf(ReminderCreatedEvent.class, reminder.getEvents().get(0));
    }

    @Test
    @DisplayName("Rename reminder test case: success")
    void renameReminderTestCaseSuccess() {
        Reminder reminder = populate();
        reminder.rename("CoolName");

        Assertions.assertInstanceOf(ReminderRenamedEvent.class, reminder.getEvents().get(1));
    }

    @Test
    @DisplayName("Rename reminder test case: failed")
    void renameReminderTestCaseFailed() {
        Reminder reminder = populate();

        Assertions.assertThrows(ReminderValidationException.class, () -> {
            reminder.rename(null);
        });
    }

    @Test
    @DisplayName("Edit reminder description test case: success")
    void editReminderDescriptionTestCaseSuccess() {
        Reminder reminder = populate();
        reminder.editDescription("CoolDescription");

        Assertions.assertInstanceOf(ReminderDescriptionEditedEvent.class, reminder.getEvents().get(1));
    }

    @Test
    @DisplayName("Edit reminder description test case: failed")
    void editReminderDescriptionTestCaseFailed() {
        Reminder reminder = populate();

        Assertions.assertThrows(ReminderValidationException.class, () -> {
            reminder.editDescription(null);
        });
    }

    @Test
    @DisplayName("Start reminder test case: success")
    void startReminderTestCaseSuccess() {
        Reminder reminder = populate();
        reminder.start();

        Assertions.assertInstanceOf(ReminderStartedEvent.class, reminder.getEvents().get(1));
    }

    @Test
    @DisplayName("Start reminder test case: failed")
    void startReminderTestCaseFailed() {
        Reminder reminder = populate();
        reminder.start();

        Assertions.assertThrows(ReminderAlreadyStartedException.class, reminder::start);
    }

    @Test
    @DisplayName("Stop reminder test case: success")
    void stopReminderTestCaseSuccess() {
        Reminder reminder = populate();
        reminder.start();
        reminder.stop();

        Assertions.assertInstanceOf(ReminderStoppedEvent.class, reminder.getEvents().get(2));
    }

    @Test
    @DisplayName("Stop reminder test case: failed")
    void stopReminderTestCaseFailed() {
        Reminder reminder = populate();

        Assertions.assertThrows(ReminderAlreadyStoppedException.class, reminder::stop);
    }

    @Test
    @DisplayName("Restart reminder test case: success")
    void restartReminderTestCaseSuccess() {
        Reminder reminder = populate();
        reminder.restart();

        Assertions.assertInstanceOf(ReminderStartedEvent.class, reminder.getEvents().get(1));
    }
}
