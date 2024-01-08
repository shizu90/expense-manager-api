package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillStartDateCommand extends Command {
    private String recurringBillId;
    private LocalDate startDate;
}
