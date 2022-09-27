package antifraud.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serial;

public class SecureUser implements DefaultSecureUser {

    @Serial
    private static final long serialVersionUID = -6690946490872875352L;

    private final User user;
    Long id;
    String name;

    public SecureUser(User user) {
        this.user = user;
        this.id = user.getId();
        this.name = user.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
