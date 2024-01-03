package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateBillCommand implements ICommand {
    private String name;
    private String comment;
    private BigDecimal amount;
    private String currencyCode;
    private String status;
    private String type;
    private String userId;
    private String categoryId;
    private String recurrenceId;
}
