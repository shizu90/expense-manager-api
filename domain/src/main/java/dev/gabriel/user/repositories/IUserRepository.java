package dev.gabriel.user.repositories;

import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.user.models.User;

import java.util.Optional;

public interface IUserRepository extends IDomainRepository {
    Optional<User> findById(String id);
    boolean existsByEmail(String email);
    User save(User user);
    void deleteById(String id);
}

