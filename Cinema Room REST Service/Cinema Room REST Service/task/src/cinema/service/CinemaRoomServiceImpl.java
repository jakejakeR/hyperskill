package cinema.service;

import cinema.config.CinemaRoomProperties;
import cinema.exception.InvalidTokenException;
import cinema.exception.OutOfBoundsException;
import cinema.exception.SeatNotAvailableException;
import cinema.model.CinemaRoom;
import cinema.model.Customer;
import cinema.model.Seat;
import cinema.model.Ticket;
import cinema.repository.CinemaRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CinemaRoomServiceImpl implements CinemaRoomService {
    @Autowired
    CinemaRoomRepository repo;
    @Autowired
    CinemaRoomProperties cinemaRoomProperties;

    @Override
    public CinemaRoom getAvailableSeats() {
        var result = new CinemaRoom();
        result.setTotalRows(cinemaRoomProperties.getTotalRows());
        result.setTotalColumns(cinemaRoomProperties.getTotalColumns());
        var seats = repo.getAvailableSeats();
        seats.forEach(this::setPrice);
        result.setAvailableSeats(repo.getAvailableSeats());
        return result;
    }

    @Override
    public Customer purchase(Seat seat) {
        if (isOutOfBounds(seat)) throw new OutOfBoundsException();
        if (!repo.delete(seat)) throw new SeatNotAvailableException();
        setPrice(seat);
        var customer = new Customer(UUID.randomUUID(), seat);
        repo.markAsPurchased(customer);
        return customer;
    }

    @Override
    public Ticket returnTicket(UUID token) {
        if (!repo.isValid(token)) throw new InvalidTokenException();
        return repo.returnTicket(token);
    }

    private int calcPrice(Seat seat) {
        return seat.getRow() <= cinemaRoomProperties.getFirstRows()
                ? cinemaRoomProperties.getFirstRowsPrice()
                : cinemaRoomProperties.getLastRowsPrice();
    }

    private void setPrice(Seat seat) {
        seat.setPrice(calcPrice(seat));
    }

    private boolean isOutOfBounds(Seat seat) {
        return seat.getRow() < 1
                || seat.getRow() > cinemaRoomProperties.getTotalRows()
                || seat.getColumn() < 1
                || seat.getColumn() > cinemaRoomProperties.getTotalColumns();
    }
}
