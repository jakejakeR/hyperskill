package antifraud.model.transaction;

import antifraud.validation.AvailableRegion;
import antifraud.validation.Ipv4;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class TransactionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Positive
    private Long amount;

    @NotBlank
    @CreditCardNumber
    private String number;

    @Ipv4
    @NotBlank
    private String ip;

    @Enumerated
    @AvailableRegion(enumClass = Region.class)
    private Region region;

    private LocalDateTime date;
}
