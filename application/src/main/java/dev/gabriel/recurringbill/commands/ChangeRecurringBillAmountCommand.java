package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillAmountCommand extends Command {
    private String recurringBillId;
    private BigDecimal amount;
}
