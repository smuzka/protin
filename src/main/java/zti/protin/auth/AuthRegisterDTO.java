package zti.protin.auth;

import lombok.Data;

@Data
public class AuthRegisterDTO {
    private String email;
    private String password;
    private String username;
    private int age;
    private String gender;
    private String experience;
    private String education;
    private String preferences;
}
