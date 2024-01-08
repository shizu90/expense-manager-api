package dev.gabriel.reminder.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeReminderRecurrenceCommand extends Command {
    private String reminderId;
    private Long recurrence;
}
