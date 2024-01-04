package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeBillCurrencyCodeCommand implements ICommand {
    private String billId;
    private String currencyCode;
}
