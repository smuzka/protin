package zti.protin.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for handling user requests
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by username
     *
     * @param username - username of the user to get
     * @return user with the given username
     */
    User findByUsername(String username);

    /**
     * Find a user by email
     *
     * @param email - email of the user to get
     * @return user with the given email
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user with the given username exists
     *
     * @param username - username to check
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Check if a user with the given email exists
     *
     * @param email - email to check
     * @return true if a user with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Get all users
     *
     * @return all users
     */
    List<User> findAll();

    /**
     * Get user by random that user with given id has not matched with
     *
     * @param id - id of the user to get
     * @return user with the given id
     */
    @Query("SELECT u FROM User u WHERE u.id != :id AND u.id NOT IN (SELECT m.matched_user_id FROM Match m WHERE m.matching_user_id = :id) AND u.id NOT IN (SELECT m.matched_user_id FROM NotMatch m WHERE m.matching_user_id = :id) ORDER BY RANDOM() LIMIT 1")
    User findUserToMatchById(Long id);
}

