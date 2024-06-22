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
import zti.protin.auth.AuthRegisterDto;

/**
 * Entity for handling user data
 */
@Getter
@Persistent
@Entity
@Table(name = "app_user")
public class User {
    /**
     * Id of the user
     */
    // Getters and setters
    @Setter
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Username of the user
     */
    @Setter
    private String username;
    /**
     * Age of the user
     */
    @Setter
    private int age;
    /**
     * Gender of the user
     */
    @Setter
    private String gender;
    /**
     * Experience of the user
     */
    @Setter
    private String experience;
    /**
     * Education of the user
     */
    @Setter
    private String education;
    /**
     * Preferences of the user
     */
    @Setter
    private String preferences;
    /**
     * Email of the user
     */
    @Setter
    @Email
    @NotEmpty
    private String email;
    /**
     * Password of the user stored as a hash
     */
    @NotEmpty
    private String password;

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor for creating a user
     *
     * @param username - username of the user
     * @param age - age of the user
     * @param gender - gender of the user
     * @param experience - experience of the user
     * @param education - education of the user
     * @param preferences - preferences of the user
     * @param email - email of the user
     */
    public User(String username, int age, String gender, String experience, String education, String preferences, String email) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.experience = experience;
        this.education = education;
        this.preferences = preferences;
        this.email = email;
    }

    /**
     * Constructor for registering a user
     *
     * @param user - user to register
     */
    public User(AuthRegisterDto user) {
        this.username = user.getUsername();
        this.age = user.getAge();
        this.gender = user.getGender();
        this.experience = user.getExperience();
        this.education = user.getEducation();
        this.preferences = user.getPreferences();
        this.email = user.getEmail();
        this.password = hashPassword(user.getPassword());
    }

    /**
     * Constructor for copying a user
     *
     * @param user - user to copy
     */
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

    /**
     * Constructor for creating a user from a DTO
     *
     * @param userDto - DTO containing user data
     */
    public User(UserDto userDto) {
        this.id = userDto.getId();
        this.username = userDto.getUsername();
        this.age = userDto.getAge();
        this.gender = userDto.getGender();
        this.experience = userDto.getExperience();
        this.education = userDto.getEducation();
        this.preferences = userDto.getPreferences();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }

    /**
     * Method for printing the user
     *
     * @return string representation of the user
     */
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

    /**
     * Method for getting the password as hash
     *
     * @param password - password to set
     */
    public void setPassword(String password) {
        // Hash the password before setting it
        this.password = hashPassword(password);
    }

    /**
     * Method for hashing the password
     *
     * @param password - password to hash
     * @return hashed password
     */
    private String hashPassword(String password) {
        // Use BCryptPasswordEncoder to hash the password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}

