package dev.gabriel.reminder.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class RestartReminderCommand extends Command {
    private UUID reminderId;
}