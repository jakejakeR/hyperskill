package antifraud.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecureUser implements DefaultSecureUser {

    @Serial
    private static final long serialVersionUID = -6690946490872875352L;

    private final User user;
    Long id;
    String name;
    String role;

    public SecureUser(User user) {
        this.user = user;
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }
}
