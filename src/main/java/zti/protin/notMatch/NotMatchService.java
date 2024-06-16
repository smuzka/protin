package zti.protin.notMatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zti.protin.match.MatchDto;
import zti.protin.user.MyUserDetailsService;
import zti.protin.user.UserDto;

@Service
public class NotMatchService {

    @Autowired
    private NotMatchRepository notMatchRepository;

    @Autowired
    private MyUserDetailsService userService;

    public Boolean notMatch(NotMatchDto matchDTO) {
        UserDto matching_user = userService.getUserDtoById(matchDTO.getMatching_user_id());
        UserDto matched_user = userService.getUserDtoById(matchDTO.getMatched_user_id());

        notMatchRepository.save(new NotMatch(matching_user, matched_user));

        return true;
    }
}
