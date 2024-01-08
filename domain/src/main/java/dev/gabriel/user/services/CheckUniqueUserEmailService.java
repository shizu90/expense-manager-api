package dev.gabriel.user.services;

import dev.gabriel.shared.services.IDomainService;
import dev.gabriel.user.models.User;
import dev.gabriel.user.valueobjects.Email;

import java.util.Optional;

public interface CheckUniqueUserEmailService extends IDomainService {
    Optional<User> getUserFromEmail(Email email);
}
