import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {

    // Mock the repository or DAO layer if present
    @Mock
    private UserRepository userRepository;

    // UserService with mocked dependencies
    @InjectMocks
    private UserService userService;

    private User testUser;

    private Long id = 1L;
    private String firstName = "John";
    private String secondName = "Doe";
    private String email = "johndoe12345@gmail.com";

    private String firstNameUpdated = "Jane";

    @BeforeEach
    public void setUp() {
        // Mockito annotations setup
        MockitoAnnotations.openMocks(this);

        // create test user
        testUser = new User(id, firstName, secondName, email);
    }

    @Test
    public void testCreateUser() throws SuchUserAlreadyExistsException {
        // describe expected behaviour
        User user = new User(id, firstName, secondName, email);
        when(userRepository.create(argThat(userArg -> !userArg.equals(testUser)))).thenReturn(null);
        when(userRepository.create(testUser)).thenReturn(Optional.of(user));

        // call create
        User createdUser = userService.createUser(testUser);

        // occurred as expected
        verify(userRepository, times(1)).create(testUser);

        // assert
        assertEquals(testUser.getId(), createdUser.getId());
        assertEquals(testUser.getFirstName(), createdUser.getFirstName());
        assertEquals(testUser.getSecondName(), createdUser.getSecondName());
        assertEquals(testUser.getEmail(), createdUser.getEmail());
    }

    @Test
    public void testGetUserById() throws NoSuchUserException {
        // describe expected behaviour
        User user = new User(id, firstName, secondName, email);
        when(userRepository.getById(argThat(id -> id != 1L))).thenReturn(null);
        when(userRepository.getById(id)).thenReturn(Optional.of(user));

        // call get by id
        User foundUser = userService.getUserById(id);

        // occurred as expected
        verify(userRepository, times(1)).getById(id);

        // assert
        assertEquals(testUser.getId(), foundUser.getId());
        assertEquals(testUser.getFirstName(), foundUser.getFirstName());
        assertEquals(testUser.getSecondName(), foundUser.getSecondName());
        assertEquals(testUser.getEmail(), foundUser.getEmail());
    }

    @Test
    public void testUpdateUser() throws NoSuchUserException {
        // describe expected behaviour
        User user = new User(id, firstNameUpdated, secondName, email);
        when(userRepository.update(argThat(userArg -> !userArg.equals(testUser)))).thenReturn(null);
        when(userRepository.update(testUser)).thenReturn(Optional.of(user));

        // change name
        testUser.setFirstName(firstNameUpdated);

        // call update
        User updatedUser = userService.updateUser(testUser);

        // occurred as expected
        verify(userRepository, times(1)).update(testUser);

        // assert
        assertEquals(testUser.getId(), updatedUser.getId());
        assertEquals(testUser.getFirstName(), updatedUser.getFirstName());
        assertEquals(testUser.getSecondName(), updatedUser.getSecondName());
        assertEquals(testUser.getEmail(), updatedUser.getEmail());
    }
}
