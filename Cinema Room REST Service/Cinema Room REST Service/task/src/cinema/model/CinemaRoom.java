package cinema.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CinemaRoom {
    int totalRows;
    int totalColumns;
    List<Seat> availableSeats = new ArrayList<>();
}
