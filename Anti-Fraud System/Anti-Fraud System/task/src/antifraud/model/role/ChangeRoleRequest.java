package antifraud.model.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class ChangeRoleRequest {
    @NotBlank
    String username;
    @NotBlank
    @Pattern(regexp = "ADMINISTRATOR|MERCHANT|SUPPORT")
    String role;
}
