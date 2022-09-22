package cinema.service;

import cinema.model.CinemaRoom;
import cinema.model.Customer;
import cinema.model.Seat;
import cinema.model.Ticket;

import java.util.UUID;

public interface CinemaRoomService {
    CinemaRoom getAvailableSeats();

    Customer purchase(Seat seat);

    Ticket returnTicket(UUID token);
}
