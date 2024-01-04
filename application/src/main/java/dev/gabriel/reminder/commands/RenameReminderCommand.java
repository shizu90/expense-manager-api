package dev.gabriel.reminder.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RenameReminderCommand implements ICommand {
    private String reminderId;
    private String name;
}
