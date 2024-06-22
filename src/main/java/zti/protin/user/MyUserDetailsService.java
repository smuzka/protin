package zti.protin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import zti.protin.auth.AuthLoginDTO;
import zti.protin.auth.AuthRegisterDto;

import java.util.List;
import java.util.Optional;

/**
 * Service for handling user details
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    /**
     * Repository for handling user requests
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Get user details by email
     *
     * @param email - email of the user to get details for
     * @return user details
     * @throws UsernameNotFoundException - exception thrown if the user is not found
     */
    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        return user.map(MyUserDetails::new).get();
    }

    /**
     * Add a user
     *
     * @param user - user to add
     */
    public void Add(AuthRegisterDto user) {
        userRepository.save(new User(user));
    }

    /**
     * Get all users
     *
     * @return all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Get user by id
     *
     * @param id - id of the user to get
     * @return user details
     */
    public UserDto getUserDtoById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            userDto.setId(user.get().getId());
            userDto.setEmail(user.get().getEmail());
            userDto.setUsername(user.get().getUsername());
            userDto.setAge(user.get().getAge());
            userDto.setGender(user.get().getGender());
            userDto.setExperience(user.get().getExperience());
            userDto.setEducation(user.get().getEducation());
            userDto.setPreferences(user.get().getPreferences());
            // set other fields as necessary
            return userDto;
        } else {
            throw new UsernameNotFoundException("User not found with id: " + id);
        }
    }

    /**
     * Check if the password is correct
     *
     * @param authLoginDTO - DTO containing email and password
     * @return true if the password is correct, false otherwise
     */
    public boolean checkPassword(AuthLoginDTO authLoginDTO) {
        // Use BCryptPasswordEncoder to check if the given password matches the hashed password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findByEmail(authLoginDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Not found: " + authLoginDTO.getEmail()));
        return encoder.matches(authLoginDTO.getPassword(), user.getPassword());
    }

    /**
     * Get user to match
     *
     * @param id - id of the user to get
     * @return user to match
     */
    public User getUserToMatch(Long id) {
        return userRepository.findUserToMatchById(id);
    }
}

