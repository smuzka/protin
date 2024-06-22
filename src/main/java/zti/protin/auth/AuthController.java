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

/**
 * Controller for handling authentication requests
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    /**
     * Service for handling authentication requests
     */
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Service for handling user requests
     */
    @Autowired
    private MyUserDetailsService userService;

    /**
     * Service for handling token blacklist requests
     */
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    /**
     * Endpoint for logging in
     *
     * @param authLoginDTO - DTO containing email and password
     * @return JWT token
     * @throws Exception - exception thrown if the email or password is incorrect
     */
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

    /**
     * Endpoint for registering a new user
     *
     * @param authRegisterDTO - DTO containing email and password
     * @return ResponseEntity with status 200 if registration was successful, 409 if email already exists
     * @throws Exception - exception thrown if the email already exists
     */
    @PostMapping("/register")
    public ResponseEntity createAuthenticationToken(@RequestBody AuthRegisterDto authRegisterDTO) throws Exception {
        try {
            userService.Add(authRegisterDTO);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
    }

    /**
     * Endpoint for logging out
     *
     * @param token - JWT token
     * @return ResponseEntity with status 200
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        tokenBlacklistService.blacklistToken(token);

        return ResponseEntity.ok("Logged out successfully");
    }

}

