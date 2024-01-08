package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillRecurrenceCommand implements ICommand {
    private String recurringBillId;
    private Long recurrence;
}
