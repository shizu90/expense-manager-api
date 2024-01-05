package dev.gabriel.user.repositories;

import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.models.User;
import dev.gabriel.user.valueobjects.Email;
import dev.gabriel.user.valueobjects.UserId;

import java.util.Optional;

public interface IUserRepository extends IDomainRepository {
    Optional<User> findById(UserId userId);
    boolean existsByEmail(Email email);
    User save(User user);
}

