package dev.gabriel.shared.valueobjects;

import dev.gabriel.shared.entities.ValueObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

public class CreatedAt extends ValueObject {
    private final Instant value;

    private CreatedAt(Instant value) {
        this.value = value;
    }

    public static CreatedAt create(Instant value) {
        return new CreatedAt(value);
    }

    public LocalDate toLocalDate() {
        return LocalDate.ofInstant(value, ZoneId.of("America/Sao_Paulo"));
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
