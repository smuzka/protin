package zti.protin.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.id != :id AND u.id NOT IN (SELECT m.matched_user_id FROM Match m WHERE m.matching_user_id = :id) AND u.id NOT IN (SELECT m.matched_user_id FROM NotMatch m WHERE m.matching_user_id = :id) ORDER BY RANDOM() LIMIT 1")
    User findUserToMatchById(Long id);
}

