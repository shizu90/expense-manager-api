package dev.gabriel.reminder.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class ReminderId extends Identity {
    private ReminderId(UUID value) {
        super(value);
    }

    public static ReminderId create(UUID value) {
        return new ReminderId(value);
    }
}
