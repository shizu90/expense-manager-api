package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillStartDateCommand implements ICommand {
    private String recurringBillId;
    private LocalDate startDate;
}
