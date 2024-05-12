package zti.protin.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Persistent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Persistent
@Entity
@Table(name = "app_user")
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private int age;
    private String gender;
    private String experience;
    private String education;
    private String preferences;
    private String email;
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

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

