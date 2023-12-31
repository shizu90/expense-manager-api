package dev.gabriel.bill.commands;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ChangeBillAmountCommand implements ICommand {
    private BillId billId;
    private BigDecimal amount;
}
