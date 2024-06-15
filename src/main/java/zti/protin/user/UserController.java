package zti.protin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/user-to-match/{userId}")
    public ResponseEntity<List<User>> getUserToMatch(@PathVariable Long userId) {
        List<User> user = userService.getUserToMatch(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
