package cinema.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@ConfigurationProperties(prefix = "cinema-room")
public class CinemaRoomProperties {
    int totalRows;
    int totalColumns;
    int firstRows;
    int firstRowsPrice;
    int lastRowsPrice;
}
