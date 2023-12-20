package dev.gabriel.shared.repositories;

import dev.gabriel.shared.exceptions.DataValidationException;
import dev.gabriel.shared.exceptions.NotFoundException;
import dev.gabriel.shared.entities.AggregateRoot;

public interface IDomainRepository<E extends AggregateRoot> {
    E get(String id) throws NotFoundException;
    E create(E entity) throws DataValidationException;
    E update(E entity) throws DataValidationException, NotFoundException;
    void delete(E entity) throws NotFoundException;
}
