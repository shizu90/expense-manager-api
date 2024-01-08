package dev.gabriel.user.commands;

import dev.gabriel.shared.commands.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RenameUserCommand extends Command {
    private String userId;
    private String name;
}
