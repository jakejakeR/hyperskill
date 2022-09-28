package antifraud.model;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class ChangeRoleRequest {
    @NotBlank
    String username;
    @NotBlank
    String role;
}
