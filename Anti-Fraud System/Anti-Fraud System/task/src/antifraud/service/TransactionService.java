package antifraud.service;

import antifraud.model.transaction.Result;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public Result process(Long amount) {
        if (amount <= 200) {
            return Result.ALLOWED;
        } else if (amount <= 1500) {
            return Result.MANUAL_PROCESSING;
        } else {
            return Result.PROHIBITED;
        }
    }
}
