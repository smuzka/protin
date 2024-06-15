package zti.protin.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import zti.protin.security.JwtUtil;
import zti.protin.security.TokenBlacklistService;
import zti.protin.user.MyUserDetailsService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyUserDetailsService userService;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthLoginDTO authLoginDTO) throws Exception {
        try {
            boolean isPasswordCorrect = userService.checkPassword(authLoginDTO);
            if (!isPasswordCorrect) {
                throw new Exception("Incorrect email or password");
            }
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final String jwt = jwtUtil.generateToken(authLoginDTO.getEmail());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity createAuthenticationToken(@RequestBody AuthRegisterDto authRegisterDTO) throws Exception {
        try {
            userService.Add(authRegisterDTO);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        tokenBlacklistService.blacklistToken(token);

        return ResponseEntity.ok("Logged out successfully");
    }

}

