package dev.gabriel.reminder.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StartReminderCommand implements ICommand {
    private String reminderId;
}
