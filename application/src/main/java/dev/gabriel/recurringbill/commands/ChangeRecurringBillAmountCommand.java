package dev.gabriel.recurringbill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChangeRecurringBillAmountCommand extends Command {
    private UUID recurringBillId;
    private BigDecimal amount;
}
