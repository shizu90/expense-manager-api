package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ChangeBillCurrencyCodeCommand extends Command {
    private UUID billId;
    private String currencyCode;
}
