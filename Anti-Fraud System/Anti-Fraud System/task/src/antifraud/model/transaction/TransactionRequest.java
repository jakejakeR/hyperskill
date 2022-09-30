package antifraud.model.transaction;

import antifraud.validation.Ipv4;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class TransactionRequest {

    @NotNull
    @Positive
    private Long amount;

    @NotBlank
    @CreditCardNumber
    private String number;

    @Ipv4
    @NotBlank
    private String ip;
}
