package dev.gabriel.wallet.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateWalletCommand extends Command {
    private String name;
    private String description;
    private BigDecimal amount;
    private String currencyCode;
    private Boolean isMain;
    private String type;
    private UUID userId;
}
