package dev.gabriel.user.repositories;

import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.models.User;
import dev.gabriel.user.valueobjects.UserId;

import java.util.Optional;

public interface IUserRepository extends IDomainRepository {
    Optional<User> load(UserId userId);
    User registerEvents(User user);
}

