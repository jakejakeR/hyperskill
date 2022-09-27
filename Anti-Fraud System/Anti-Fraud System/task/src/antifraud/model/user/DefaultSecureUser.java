package antifraud.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

public interface DefaultSecureUser extends UserDetails {

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
