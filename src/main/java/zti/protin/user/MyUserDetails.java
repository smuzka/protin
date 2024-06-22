package zti.protin.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Class for handling user details
 */
public class MyUserDetails implements UserDetails {

    /**
     * User to get details for
     */
    private User user;

    /**
     * Constructor for MyUserDetails
     *
     * @param user - user to get details for
     */
    public MyUserDetails(User user) {
        this.user = user;
    }

    /**
     * @return authorities of the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Get the user
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * Get the password of the user
     *
     * @return password of the user
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Get the username of the user
     *
     * @return username of the user
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Check if the account is not expired
     *
     * @return true if the account is not expired, false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if the account is not locked
     *
     * @return true if the account is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if the credentials are not expired
     *
     * @return true if the credentials are not expired, false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Check if the account is enabled
     *
     * @return true if the account is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
