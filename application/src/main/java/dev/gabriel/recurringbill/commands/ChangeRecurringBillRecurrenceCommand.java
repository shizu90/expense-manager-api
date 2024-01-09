package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillRecurrenceCommand extends Command {
    private UUID recurringBillId;
    private Long recurrence;
}
