package zti.protin.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.protin.user.MyUserDetailsService;
import zti.protin.user.User;
import zti.protin.user.UserDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MyUserDetailsService userService;

    public Boolean match(MatchDto matchDTO) {
        UserDto matching_user = userService.getUserDtoById(matchDTO.getMatching_user_id());
        UserDto matched_user = userService.getUserDtoById(matchDTO.getMatched_user_id());

        matchRepository.save(new Match(matching_user, matched_user));

        return true;
    }

    public List<User> getMutualMatches(Long userId) {
        List<Long> userIds = matchRepository.findMutualMatches(userId);
        List<User> users = userIds.stream().map(userService::getUserDtoById).map(User::new).collect(Collectors.toList());
        return users.stream().map(User::new).collect(Collectors.toList());
    }
}
