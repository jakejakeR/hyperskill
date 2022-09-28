package antifraud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ChangeRoleRequest {
    @NotBlank
    String username;
    @NotBlank
    String role;
}
