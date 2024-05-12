package zti.protin.user;

import java.util.List;

public interface UserService {

    User registerUser(User user);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);

    List<User> getAllUsers();
}
