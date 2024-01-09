package dev.gabriel.user.eventstore;

import dev.gabriel.user.models.User;
import dev.gabriel.user.repositories.IUserRepository;
import dev.gabriel.user.valueobjects.UserId;

import java.util.Optional;

public class UserEventStore implements IUserRepository {
    @Override
    public Optional<User> load(UserId userId) {
        return Optional.empty();
    }

    @Override
    public User registerEvents(User user) {
        return null;
    }
}
