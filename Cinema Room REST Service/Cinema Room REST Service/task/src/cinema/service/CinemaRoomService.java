package cinema.service;

import cinema.model.*;

import java.util.UUID;

public interface CinemaRoomService {
    CinemaRoom getAvailableSeats();

    Customer purchase(Seat seat);

    Ticket returnTicket(UUID token);

    Statistics showStats(String password);
}
