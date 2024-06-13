package zti.protin.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zti.protin.user.MyUserDetailsService;

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
}
