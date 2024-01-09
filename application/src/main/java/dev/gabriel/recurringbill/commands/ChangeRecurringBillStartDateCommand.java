package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillStartDateCommand extends Command {
    private UUID recurringBillId;
    private LocalDate startDate;
}
