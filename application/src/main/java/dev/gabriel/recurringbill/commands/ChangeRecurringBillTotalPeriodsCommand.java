package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillTotalPeriodsCommand implements ICommand {
    private String recurringBillId;
    private Long totalPeriods;
}