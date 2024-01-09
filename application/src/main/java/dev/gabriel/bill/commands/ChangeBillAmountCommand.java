package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChangeBillAmountCommand extends Command {
    private UUID billId;
    private BigDecimal amount;
}
