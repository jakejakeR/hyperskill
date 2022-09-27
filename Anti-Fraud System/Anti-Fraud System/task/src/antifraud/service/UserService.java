package antifraud.service;

import antifraud.model.SecureUser;
import antifraud.model.User;
import antifraud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecureUser(
            userRepository.findByUsernameIgnoreCase(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User details not found for user: " + username))
        );
    }

    public Optional<SecureUser> register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(new SecureUser(user));
    }
}
