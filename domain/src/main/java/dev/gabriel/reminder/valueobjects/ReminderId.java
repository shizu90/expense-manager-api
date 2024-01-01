package dev.gabriel.reminder.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class ReminderId extends Identity {
    private ReminderId(String value) {
        super(value);
    }

    public static ReminderId create(String value) {
        return new ReminderId(value);
    }
}
