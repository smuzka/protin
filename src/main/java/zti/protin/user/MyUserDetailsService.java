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

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        return user.map(MyUserDetails::new).get();
    }

    public void Add(AuthRegisterDto user) {
        userRepository.save(new User(user));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

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

    public boolean checkPassword(AuthLoginDTO authLoginDTO) {
        // Use BCryptPasswordEncoder to check if the given password matches the hashed password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findByEmail(authLoginDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Not found: " + authLoginDTO.getEmail()));
        return encoder.matches(authLoginDTO.getPassword(), user.getPassword());
    }

    public User getUserToMatch(Long id) {
        return userRepository.findUserToMatchById(id);
    }
}

