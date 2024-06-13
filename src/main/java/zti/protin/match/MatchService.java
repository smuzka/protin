package zti.protin.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.protin.user.UserDto;
import zti.protin.user.MyUserDetailsService;

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


}
