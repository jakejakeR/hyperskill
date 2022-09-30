package antifraud.model.transaction;

import lombok.Value;

@Value
public class TransactionResponse {
    Result result;
    String info;
}
