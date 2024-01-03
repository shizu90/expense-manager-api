package dev.gabriel.user.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

@Getter
public class RenameUserCommand implements ICommand {
    private String userId;
    private String name;
}
