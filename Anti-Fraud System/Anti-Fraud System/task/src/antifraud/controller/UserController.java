package antifraud.controller;

import antifraud.model.access.ChangeAccessRequest;
import antifraud.model.role.ChangeRoleRequest;
import antifraud.model.user.SecureUser;
import antifraud.model.user.User;
import antifraud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public SecureUser register(@Valid @RequestBody User user) {
        return userService.register(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
    }

    @PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'SUPPORT')")
    @GetMapping("/list")
    public List<SecureUser> listUsers() {
        return userService.listUsers();
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @DeleteMapping("/user/{username}")
    public Map<String, String> deleteUser(@PathVariable("username") @NotBlank String username) {
        return userService.deleteUser(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @DeleteMapping("/user/")
    public void deleteUser() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @PutMapping("/role")
    public SecureUser changeRole(@Valid @RequestBody ChangeRoleRequest changeRoleRequest) {
        return userService.changeRole(changeRoleRequest);
    }

    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    @PutMapping("/access")
    public Map<String, String> changeAccess(@Valid @RequestBody ChangeAccessRequest changeAccessRequest) {
        return userService.changeAccess(changeAccessRequest);
    }
}
