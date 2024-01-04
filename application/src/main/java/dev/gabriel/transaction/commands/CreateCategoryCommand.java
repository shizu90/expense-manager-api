package dev.gabriel.transaction.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCategoryCommand implements ICommand {
    private String name;
    private String userId;
}
