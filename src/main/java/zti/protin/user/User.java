package zti.protin.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Persistent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zti.protin.auth.AuthRegisterDTO;

@Getter
@Persistent
@Entity
@Table(name = "app_user")
public class User {
    // Getters and setters
    @Setter
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String username;
    @Setter
    private int age;
    @Setter
    private String gender;
    @Setter
    private String experience;
    @Setter
    private String education;
    @Setter
    private String preferences;
    @Setter
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

    // Constructors, getters, and setters
    public User() {
    }

    public User(String username, int age, String gender, String experience, String education, String preferences, String email) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.experience = experience;
        this.education = education;
        this.preferences = preferences;
        this.email = email;
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.experience = user.getExperience();
        this.education = user.getEducation();
        this.preferences = user.getPreferences();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public User(AuthRegisterDTO user) {
        this.username = user.getUsername();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.experience = user.getExperience();
        this.education = user.getEducation();
        this.preferences = user.getPreferences();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", experience='" + experience + '\'' +
                ", education='" + education + '\'' +
                ", preferences='" + preferences + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setPassword(String password) {
        // Hash the password before setting it
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        // Use BCryptPasswordEncoder to hash the password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public boolean checkPassword(String password) {
        // Use BCryptPasswordEncoder to check if the given password matches the hashed password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, this.password);
    }
}

