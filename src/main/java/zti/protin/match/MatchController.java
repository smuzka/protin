package zti.protin.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.protin.user.User;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/match")
    public ResponseEntity<Boolean> match(@RequestBody MatchDto matchDTO) {
        boolean match = matchService.match(matchDTO);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @GetMapping("/mutual-matches/{userId}")
    public ResponseEntity<List<User>> getMutualMatches(@PathVariable Long userId) {
        List<User> users = matchService.getMutualMatches(userId);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
