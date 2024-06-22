package zti.protin.notMatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zti.protin.user.MyUserDetails;

/**
 * Controller for handling not-match requests
 */
@RestController
@RequestMapping("/api")
public class NotMatchController {

    /**
     * Service for handling not-match requests
     */
    @Autowired
    private NotMatchService notMatchService;

    /**
     * Endpoint for not-matching two users
     *
     * @param userId - id of the user to not match with
     * @return true if the not-match was successful, false otherwise
     */
    @PostMapping("/not-match/{userId}")
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

        NotMatchDto notMatch = new NotMatchDto(matchingUserId, userId);

        boolean isMatch = notMatchService.notMatch(notMatch);
        return new ResponseEntity<>(isMatch, HttpStatus.OK);
    }
}
