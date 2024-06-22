package zti.protin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling user requests
 */
@RestController
@RequestMapping("/api")
public class UserController {

    /**
     * Service for handling user details
     */
    @Autowired
    private MyUserDetailsService userService;

    /**
     * Get all users
     *
     * @return all users
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get a user by id
     *
     * @param userId - id of the user to get
     * @return
     */
    @GetMapping("user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto user = userService.getUserDtoById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Get user to match
     *
     * @return user to match
     */
    @GetMapping("/user-to-match")
    public ResponseEntity<User> getUserToMatch() {
        Long userId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                userId = ((MyUserDetails) principal).getUser().getId();
            }
        }

        if (userId == null) {
            return null;
        }

        User user = userService.getUserToMatch(userId);
        if (user == null) {
            return null;
        }

        user.setEmail("hidden");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Get current user
     *
     * @return current user
     */
    @GetMapping("/me")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((MyUserDetails) principal).getUser();
            } else {
                return null;
            }
        }
        return null;
    }
}
