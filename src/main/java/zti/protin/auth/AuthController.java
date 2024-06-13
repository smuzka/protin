package zti.protin.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zti.protin.security.JwtUtil;
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

    @PostMapping("/login")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthLoginDTO authLoginDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authLoginDTO.getEmail(), authLoginDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect email or password", e);
        }

        final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService.loadUserByUsername(authLoginDTO.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity createAuthenticationToken(@RequestBody AuthRegisterDTO authRegisterDTO) throws Exception {
        userService.Add(authRegisterDTO);

        return ResponseEntity.ok().build();
    }
}

