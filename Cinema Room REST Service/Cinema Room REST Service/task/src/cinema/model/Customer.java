package cinema.model;

import lombok.Value;

import java.util.UUID;

@Value
public class Customer {
    UUID token;
    Seat ticket;
}