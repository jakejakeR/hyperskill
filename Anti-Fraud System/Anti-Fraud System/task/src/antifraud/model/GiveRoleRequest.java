package antifraud.model;

import lombok.Value;

@Value
public class GiveRoleRequest {
    String username;
    String role;
}
