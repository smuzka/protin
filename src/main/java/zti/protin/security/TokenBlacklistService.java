package zti.protin.security;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

/**
 * Service for handling blacklisted tokens
 */
@Service
public class TokenBlacklistService {
    /**
     * Set of blacklisted tokens
     */
    private Set<String> blacklistedTokens = new HashSet<>();

    /**
     * Blacklist a token
     *
     * @param token - token to blacklist
     */
    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    /**
     * Check if a token is blacklisted
     *
     * @param token - token to check
     * @return true if the token is blacklisted, false otherwise
     */
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
