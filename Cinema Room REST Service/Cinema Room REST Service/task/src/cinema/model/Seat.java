package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seat {
    int row;
    int column;
    @EqualsAndHashCode.Exclude
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int price;
}