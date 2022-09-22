package cinema.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Statistics {
    int currentIncome;
    int numberOfAvailableSeats;
    int numberOfPurchasedTickets;
}
