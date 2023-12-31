package dev.gabriel.bill.commands;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.commands.ICommand;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

@Getter
public class ChangeBillCurrencyCodeCommand implements ICommand {
    private BillId billId;
    private CurrencyCode currencyCode;
}
