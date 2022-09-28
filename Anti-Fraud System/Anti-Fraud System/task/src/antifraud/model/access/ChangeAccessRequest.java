package antifraud.model.access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class ChangeAccessRequest {
    @NotBlank
    String username;
    @NotBlank
    @Pattern(regexp = "LOCK|UNLOCK")
    String operation;
}
