package antifraud.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public interface DefaultSecureUser extends UserDetails {
    @Override
    @JsonIgnore
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    @JsonIgnore
    default boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    default boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    default boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    default boolean isEnabled() {
        return true;
    }
}
