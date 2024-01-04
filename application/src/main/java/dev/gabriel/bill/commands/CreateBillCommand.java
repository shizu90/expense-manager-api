package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CreateBillCommand implements ICommand {
    private String name;
    private String comment;
    private BigDecimal amount;
    private String currencyCode;
    private String type;
    private String categoryId;
    private String walletId;
}
