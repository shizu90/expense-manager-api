package dev.gabriel.validations;

import dev.gabriel.primitives.Entity;

import java.util.List;

public interface IValidator<T extends Entity> {
    List<String> validate(T entity);
}
