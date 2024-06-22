package zti.protin.notMatch;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import zti.protin.user.UserDto;

/**
 * Entity representing a not match between two users
 */
@Getter
@Setter
@Entity
@Table(name = "not_matches")
public class NotMatch {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Long matching_user_id;
    @NotEmpty
    private Long matched_user_id;

    /**
     * Constructor for creating a not match
     *
     * @param matching_user - user who initiated the not match
     * @param matched_user - user who was not matched
     */
    public NotMatch(UserDto matching_user, UserDto matched_user) {
        this.matching_user_id = matching_user.getId();
        this.matched_user_id = matched_user.getId();
    }

    public NotMatch() {
    }
}
