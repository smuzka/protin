package zti.protin.auth;

import lombok.Data;

/**
 * DTO for registration requests
 */
@Data
public class AuthRegisterDto {
    private String email;
    private String password;
    private String username;
    private int age;
    private String gender;
    private String experience;
    private String education;
    private String preferences;
}
