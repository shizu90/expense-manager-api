package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateWalletCommand implements ICommand {
    private String name;
    private String description;
    private BigDecimal amount;
    private String currencyCode;
    private Boolean isMain;
    private String type;
    private String userId;
}
