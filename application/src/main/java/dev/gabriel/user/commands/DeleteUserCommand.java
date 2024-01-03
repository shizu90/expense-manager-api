package dev.gabriel.user.commands;

import dev.gabriel.shared.commands.ICommand;
import lombok.Getter;

@Getter
public class DeleteUserCommand implements ICommand {
    private String userId;
}
