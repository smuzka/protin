package zti.protin.auth;

import lombok.Data;

/**
 * DTO for login requests
 */
@Data
public class AuthLoginDTO {
    private String email;
    private String password;
}

