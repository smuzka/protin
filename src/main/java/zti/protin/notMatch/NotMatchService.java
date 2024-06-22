package zti.protin.notMatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.protin.match.MatchDto;
import zti.protin.user.MyUserDetailsService;
import zti.protin.user.UserDto;

/**
 * Service for handling not match requests
 */
@Service
public class NotMatchService {

    /**
     * Repository for handling not match requests
     */
    @Autowired
    private NotMatchRepository notMatchRepository;

    /**
     * Service for handling user requests
     */
    @Autowired
    private MyUserDetailsService userService;

    /**
     * Not match two users
     *
     * @param matchDTO - DTO containing the ids of the users to not match
     * @return true if the not match was successful, false otherwise
     */
    public Boolean notMatch(NotMatchDto matchDTO) {
        UserDto matching_user = userService.getUserDtoById(matchDTO.getMatching_user_id());
        UserDto matched_user = userService.getUserDtoById(matchDTO.getMatched_user_id());

        notMatchRepository.save(new NotMatch(matching_user, matched_user));

        return true;
    }
}
