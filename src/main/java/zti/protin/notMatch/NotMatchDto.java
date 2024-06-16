package zti.protin.notMatch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotMatchDto {
    private Long matching_user_id;
    private Long matched_user_id;

    NotMatchDto(Long matching_user_id, Long matched_user_id) {
        this.matching_user_id = matching_user_id;
        this.matched_user_id = matched_user_id;
    }
}
