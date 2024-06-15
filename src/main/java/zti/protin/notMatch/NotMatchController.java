package zti.protin.notMatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zti.protin.match.MatchDto;
import zti.protin.match.MatchService;
import zti.protin.user.User;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotMatchController {

    @Autowired
    private NotMatchService notMatchService;

    @PostMapping("/not-match")
    public ResponseEntity<Boolean> match(@RequestBody MatchDto matchDTO) {
        boolean match = notMatchService.notMatch(matchDTO);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }
}
