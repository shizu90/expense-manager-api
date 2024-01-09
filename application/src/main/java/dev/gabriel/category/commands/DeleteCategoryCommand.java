package dev.gabriel.category.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeleteCategoryCommand extends Command {
    private UUID categoryId;
}
