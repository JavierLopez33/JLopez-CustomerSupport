package com.example.jlopezcustomersupport.site;

import com.example.jlopezcustomersupport.entities.UserPrincipal;
import jakarta.inject.Inject;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class DefaultAuthenticationService implements AuthenticationService{
    private static final SecureRandom RANDOM; //  crypto random number generator
    private static final int HASHING_ROUNDS = 10;

    static {
        try {
            RANDOM = SecureRandom.getInstanceStrong();
        }
        catch(NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    @Inject UserPrincipalRepository userRepo;

    @Override
    public UserPrincipal authenticate(String username, String password) {
        UserPrincipal principal = userRepo.getByUsername(username);
        if (principal == null) {
            return null;
        }

        // username found NOT correct password
        if (!BCrypt.checkpw(password, new String(principal.getPassword(), StandardCharsets.UTF_8))) {
            return null;
        }

        // password is correct
        return principal;
    }

    @Override
    public void saveUser(UserPrincipal principal, String newPassword) {
        // check if new password and hash it
        if (newPassword != null && newPassword.length() > 0) {
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            principal.setPassword(BCrypt.hashpw(newPassword, salt).getBytes());
        }

        // checks if new user
        if (principal.getId() < 1) {
            this.userRepo.add(principal);
        }
        else { // not new user just updating password
            this.userRepo.update(principal);
        }
    }
}
