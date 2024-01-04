package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillAmountCommand implements ICommand {
    private String recurringBillId;
    private BigDecimal amount;
}
