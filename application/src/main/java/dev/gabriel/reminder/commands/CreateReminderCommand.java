package dev.gabriel.reminder.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateReminderCommand extends Command {
    private String name;
    private String description;
    private Long recurrence;
    private Long maxRuns;
    private UUID userId;
}
