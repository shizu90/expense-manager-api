package dev.gabriel.category.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RenameCategoryCommand implements ICommand {
    private String categoryId;
    private String name;
}
