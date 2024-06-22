package zti.protin.match;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for handling match requests
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    /**
     * @return list of all matches
     */
    List<Match> findAll();

    /**
     * @param userId - id of the user to match with
     * @return list of matches where the user is the matching user
     */
    @Query("SELECT m1.matched_user_id FROM Match m1 " +
            "WHERE m1.matching_user_id = :userId AND EXISTS (" +
            "SELECT m2 FROM Match m2 WHERE m2.matching_user_id = m1.matched_user_id " +
            "AND m2.matched_user_id = :userId)")
    List<Long> findMutualMatches(@Param("userId") Long userId);
}
