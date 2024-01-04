package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ChangeBillAmountCommand implements ICommand {
    private String billId;
    private BigDecimal amount;
}
