package dev.gabriel.bill.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateBillCommand extends Command {
    private String name;
    private String comment;
    private BigDecimal amount;
    private String currencyCode;
    private String type;
    private UUID categoryId;
    private UUID walletId;
}
