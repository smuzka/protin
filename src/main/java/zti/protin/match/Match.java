package zti.protin.match;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import zti.protin.user.UserDto;

@Getter
@Setter
@Entity
@Table(name = "matches")
public class Match {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Long matching_user_id;
    @NotEmpty
    private Long matched_user_id;

    public Match(UserDto matching_user, UserDto matched_user) {
        this.matching_user_id = matching_user.getId();
        this.matched_user_id = matched_user.getId();
    }

    public Match() {
    }
}
