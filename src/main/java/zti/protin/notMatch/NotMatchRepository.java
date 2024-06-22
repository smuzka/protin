package zti.protin.notMatch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for handling not match requests
 */
@Repository
public interface NotMatchRepository extends JpaRepository<NotMatch, Long> {
    /**
     * @return list of all not matches
     */
    List<NotMatch> findAll();
}
