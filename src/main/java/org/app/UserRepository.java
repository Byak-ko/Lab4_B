package org.app;

import java.util.Optional;

public interface UserRepository {
    Optional<User> create(User user);
    Optional<User> getById(Long id); // avoid null returns, enable methods like isPresent() and orElse()
    Optional<User> update(User user);
}
