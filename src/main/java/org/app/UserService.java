package org.app;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws SuchUserAlreadyExistsException {
        return userRepository.create(user).orElseThrow(SuchUserAlreadyExistsException::new);
    }

    public User getUserById(Long id) throws NoSuchUserException {
        return userRepository.getById(id).orElseThrow(NoSuchUserException::new);
    }

    public User updateUser(User user) throws NoSuchUserException {
        return userRepository.update(user).orElseThrow(NoSuchUserException::new);
    }
}
