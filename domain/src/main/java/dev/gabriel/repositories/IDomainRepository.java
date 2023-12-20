package dev.gabriel.repositories;

import dev.gabriel.exceptions.DataValidationException;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.primitives.AggregateRoot;

public interface IDomainRepository<E extends AggregateRoot> {
    E get(String id) throws NotFoundException;
    E create(E entity) throws DataValidationException;
    E update(E entity) throws DataValidationException, NotFoundException;
    void delete(E entity) throws NotFoundException;
}
