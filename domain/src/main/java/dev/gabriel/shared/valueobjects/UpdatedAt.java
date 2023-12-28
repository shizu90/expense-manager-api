package dev.gabriel.shared.valueobjects;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

@Getter
public class UpdatedAt extends ValueObject {
    private final Instant value;

    private UpdatedAt(Instant value) {
        this.value = value;
    }

    public static UpdatedAt create(Instant value) {
        return new UpdatedAt(value);
    }

    public LocalDate toLocalDate() {
        return LocalDate.ofInstant(value, ZoneId.of("America/Sao_Paulo"));
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
