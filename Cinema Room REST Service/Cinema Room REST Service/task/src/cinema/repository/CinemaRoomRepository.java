package cinema.repository;

import cinema.config.CinemaRoomProperties;
import cinema.model.Customer;
import cinema.model.Seat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Data
@Repository
public class CinemaRoomRepository {
    @Autowired
    CinemaRoomProperties cinemaRoomProperties;
    List<Seat> availableSeats = new ArrayList<>();
    Map<UUID, Customer> purchasedSeats = new ConcurrentHashMap<>();

    @PostConstruct
    void initAvailableSeats() {
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= cinemaRoomProperties.getTotalRows(); i++) {
            for (int j = 1; j <= cinemaRoomProperties.getTotalColumns(); j++) {
                availableSeats.add(new Seat(i, j, 0));
            }
        }
    }

    public boolean delete(Seat seat) {
        return availableSeats.remove(seat);
    }

    public void markAsPurchased(Customer customer) {
        purchasedSeats.put(customer.getToken(), customer);
    }

    public boolean isValid(UUID token) {
        return purchasedSeats.containsKey(token);
    }
}
