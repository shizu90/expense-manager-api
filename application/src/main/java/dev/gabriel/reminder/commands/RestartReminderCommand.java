package dev.gabriel.reminder.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestartReminderCommand implements ICommand {
    private String reminderId;
}