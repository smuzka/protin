package zti.protin.match;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for match requests
 */
@Getter
@Setter
public class MatchDto {
    private Long matching_user_id;
    private Long matched_user_id;

    /**
     * @param matching_user_id - id of the user who initiated the match
     * @param matched_user_id - id of the user who was matched
     */
    MatchDto (Long matching_user_id, Long matched_user_id) {
        this.matching_user_id = matching_user_id;
        this.matched_user_id = matched_user_id;
    }
}
