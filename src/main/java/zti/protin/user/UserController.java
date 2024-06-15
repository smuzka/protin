package zti.protin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import zti.protin.auth.AuthRegisterDto;

import java.util.List;

import static java.sql.DriverManager.println;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private MyUserDetailsService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto user = userService.getUserDtoById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user-to-match/{userId}")
    public ResponseEntity<List<User>> getUserToMatch(@PathVariable Long userId) {
        List<User> user = userService.getUserToMatch(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/me")
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else {
                return principal.toString();
            }
        }
        return "No user is logged in";
    }
}
