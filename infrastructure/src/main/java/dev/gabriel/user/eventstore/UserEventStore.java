package dev.gabriel.user.eventstore;

import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.Email;
import dev.gabriel.user.valueobjects.UserId;

import java.util.Optional;

public class UserEventStore implements IUserRepository {
    @Override
    public Optional<User> findById(UserId userId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(Email email) {
        return false;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
