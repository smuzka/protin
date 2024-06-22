package zti.protin.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import zti.protin.user.MyUserDetails;
import zti.protin.user.User;

import java.util.List;

/**
 * Controller for handling match requests
 */
@RestController
@RequestMapping("/api")
public class MatchController {

    /**
     * Service for handling match requests
     */
    @Autowired
    private MatchService matchService;

    /**
     * Endpoint for matching two users
     *
     * @param userId - id of the user to match with
     * @return true if the match was successful, false otherwise
     */
    @PostMapping("/match/{userId}")
    public ResponseEntity<Boolean> match(@PathVariable Long userId) {
        Long matchingUserId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                matchingUserId = ((MyUserDetails) principal).getUser().getId();
            }
        }

        if (matchingUserId == null) {
            return null;
        }

        MatchDto match = new MatchDto(matchingUserId, userId);

        boolean isMatch = matchService.match(match);
        return new ResponseEntity<>(isMatch, HttpStatus.OK);
    }

    /**
     * Endpoint for getting a list of users that have matched with the current user
     *
     * @return list of users that have matched with the current user
     */
    @GetMapping("/mutual-matches")
    public ResponseEntity<List<User>> getMutualMatches() {
        Long userId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                userId = ((MyUserDetails) principal).getUser().getId();
            }
        }

        if (userId == null) {
            return null;
        }

        List<User> users = matchService.getMutualMatches(userId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
