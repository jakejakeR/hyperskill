package cinema.controller;

import cinema.model.*;
import cinema.service.CinemaRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaRoomController {

    @Autowired
    CinemaRoomService cinemaRoomService;

    @GetMapping("/seats")
    CinemaRoom getAvailableSeats() {
        return cinemaRoomService.getAvailableSeats();
    }

    @PostMapping("/purchase")
    Customer purchase(@RequestBody Seat seat) {
        return cinemaRoomService.purchase(seat);
    }

    @PostMapping("/return")
    Ticket returnTicket(@RequestBody TicketToken token) {
        return cinemaRoomService.returnTicket(token.getToken());
    }
}
