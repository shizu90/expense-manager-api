package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExecuteRecurringBillCommand extends Command {
    private String recurringBillId;
    private Long numberOfPeriods;
}
