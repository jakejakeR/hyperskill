package antifraud.model.transaction;

import lombok.Value;

@Value
public class TransactionResponse {

    Result result;

    public static TransactionResponse of(Result result) {
        return new TransactionResponse(result);
    }
}
