package antifraud.service;

import antifraud.model.RoleStr;
import antifraud.model.user.SecureUser;
import antifraud.model.user.User;
import antifraud.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Transactional
    public Optional<SecureUser> register(User user) {
        if (userRepository.existsByUsernameIgnoreCase(user.getUsername())) {
            return Optional.empty();
        }

        if (userRepository.count() == 0) {
            user.setRole(RoleStr.ADMINISTRATOR);
            user.setAccountNonLocked(true);
        } else {
            user.setRole(RoleStr.MERCHANT);
            user.setAccountNonLocked(false);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(new SecureUser(userRepository.save(user)));
    }

    public List<SecureUser> listUsers() {
        List<SecureUser> secureUsers = new ArrayList<>();
        userRepository.findAll(Sort.sort(User.class)
                        .by(User::getId)
                        .ascending())
                .forEach(user -> secureUsers.add(new SecureUser(user)));
        return secureUsers;
    }

    @Transactional
    public Optional<Map<String, String>> deleteUser(String username) {
        if (userRepository.deleteByUsernameIgnoreCase(username) == 0) {
            return Optional.empty();
        }
        return Optional.of(
                Map.of(
                        "username", username,
                        "status", "Deleted successfully!"
                )
        );
    }
}