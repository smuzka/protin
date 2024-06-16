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

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

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
