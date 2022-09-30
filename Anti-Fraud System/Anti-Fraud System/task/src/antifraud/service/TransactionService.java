package antifraud.service;

import antifraud.model.transaction.Result;
import antifraud.model.transaction.TransactionRequest;
import antifraud.model.transaction.TransactionResponse;
import antifraud.repository.StolenCardRepository;
import antifraud.repository.SuspiciousIpRepository;
import antifraud.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {

    private static final String AMOUNT_CARD_IP = "amount, card-number, ip";
    private static final String AMOUNT_CARD = "amount, card-number";
    private static final String AMOUNT_IP = "amount, ip";
    private static final String CARD_IP = "card-number, ip";
    private static final String AMOUNT = "amount";

    StolenCardRepository stolenCardRepository;
    SuspiciousIpRepository suspiciousIpRepository;
    TransactionRepository transactionRepository;

    public TransactionResponse process(TransactionRequest transaction) {

        transactionRepository.save(transaction);

        Long amount = transaction.getAmount();

        boolean isBlackIp = suspiciousIpRepository.existsByIp(transaction.getIp());
        boolean isBlackCard = stolenCardRepository.existsByNumber(transaction.getNumber());
        boolean isAmountTooHigh = amount > 1500;

        if (isAmountTooHigh && isBlackCard && isBlackIp) {
            return new TransactionResponse(Result.PROHIBITED, AMOUNT_CARD_IP);
        } else if (isAmountTooHigh && isBlackCard) {
            return new TransactionResponse(Result.PROHIBITED, AMOUNT_CARD);
        } else if (isAmountTooHigh && isBlackIp) {
            return new TransactionResponse(Result.PROHIBITED, AMOUNT_IP);
        } else if (isBlackCard && isBlackIp) {
            return new TransactionResponse(Result.PROHIBITED, CARD_IP);
        } else if (isBlackCard) {
            return new TransactionResponse(Result.PROHIBITED, "card-number");
        } else if (isBlackIp) {
            return new TransactionResponse(Result.PROHIBITED, "ip");
        }

        if (amount <= 200) {
            return new TransactionResponse(Result.ALLOWED, "none");
        } else if (amount <= 1500) {
            return new TransactionResponse(Result.MANUAL_PROCESSING, AMOUNT);
        } else {
            return new TransactionResponse(Result.PROHIBITED, AMOUNT);
        }
    }
}
