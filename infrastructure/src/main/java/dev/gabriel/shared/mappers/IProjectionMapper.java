package dev.gabriel.shared.mappers;

import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.projection.AggregateRootProjection;

public interface IProjectionMapper<TProjection extends AggregateRootProjection, TEntity extends AggregateRoot> {
    TEntity toDomain(TProjection tProjection);
}
