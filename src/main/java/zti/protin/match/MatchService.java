package zti.protin.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.protin.user.MyUserDetailsService;
import zti.protin.user.User;
import zti.protin.user.UserDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for handling match requests
 */
@Service
public class MatchService {

    /**
     * Repository for handling match requests
     */
    @Autowired
    private MatchRepository matchRepository;

    /**
     * Service for handling user requests
     */
    @Autowired
    private MyUserDetailsService userService;

    /**
     * Matches two users
     *
     * @param matchDTO - DTO containing the ids of the users to match
     * @return true if the match was successful, false otherwise
     */
    public Boolean match(MatchDto matchDTO) {
        UserDto matching_user = userService.getUserDtoById(matchDTO.getMatching_user_id());
        UserDto matched_user = userService.getUserDtoById(matchDTO.getMatched_user_id());

        matchRepository.save(new Match(matching_user, matched_user));

        return true;
    }

    /**
     * Gets a list of users that have matched with the current user
     *
     * @param userId - id of the user to get mutual matches with
     * @return list of users that have matched with the user
     */
    public List<User> getMutualMatches(Long userId) {
        List<Long> userIds = matchRepository.findMutualMatches(userId);
        List<User> users = userIds.stream().map(userService::getUserDtoById).map(User::new).collect(Collectors.toList());
        return users.stream().map(User::new).collect(Collectors.toList());
    }
}
