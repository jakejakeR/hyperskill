package antifraud.service;

import antifraud.model.transaction.Result;
import antifraud.model.transaction.TransactionRequest;
import antifraud.model.transaction.TransactionResponse;
import antifraud.repository.StolenCardRepository;
import antifraud.repository.SuspiciousIpRepository;
import antifraud.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionService {
    StolenCardRepository stolenCardRepository;
    SuspiciousIpRepository suspiciousIpRepository;
    TransactionRepository transactionRepository;

    @Transactional
    public TransactionResponse processAbstract(TransactionRequest transaction) {
        transactionRepository.save(transaction);

        Result amountCheck = checkAmount(transaction.getAmount());
        Result ipCheck = checkIp(transaction.getIp());
        Result cardNumberCheck = checkCard(transaction.getNumber());
        Result regionCorrelationCheck = checkCorrelation(transaction, TransactionRequest::getRegion);
        Result ipCorrelationCheck = checkCorrelation(transaction, TransactionRequest::getIp);

        Result result = Stream.of(
                        amountCheck,
                        ipCheck,
                        cardNumberCheck,
                        regionCorrelationCheck,
                        ipCorrelationCheck
                )
                .max(Comparator.comparing(Enum::ordinal)).get();

        if (result == Result.ALLOWED) {
            return new TransactionResponse(result, "none");
        }

        String info = (result == amountCheck ? "amount " : "") +
                (result == cardNumberCheck ? "card-number " : "") +
                (result == ipCheck ? "ip " : "") +
                (result == ipCorrelationCheck ? "ip-correlation " : "") +
                (result == regionCorrelationCheck ? "region-correlation" : "");

        return new TransactionResponse(result, info.trim().replace(" ", ", "));
    }

    private Result checkAmount(Long amount) {
        if (amount <= 200) {
            return Result.ALLOWED;
        }
        return amount <= 1500 ? Result.MANUAL_PROCESSING : Result.PROHIBITED;
    }

    private Result checkIp(String ip) {
        return suspiciousIpRepository.existsByIp(ip) ?
                Result.PROHIBITED : Result.ALLOWED;
    }

    private Result checkCard(String number) {
        return stolenCardRepository.existsByNumber(number) ?
                Result.PROHIBITED : Result.ALLOWED;
    }

    private Result checkCorrelation(TransactionRequest transaction, Function<TransactionRequest, String> mapper) {
        long count = transactionRepository
                .findByNumberAndDateBetween(
                        transaction.getNumber(),
                        transaction.getDate().minusHours(1),
                        transaction.getDate()
                )
                .stream()
                .map(mapper)
                .distinct()
                .count() - 1;

        if (count > 2) {
            return Result.PROHIBITED;
        }
        return count == 2 ? Result.MANUAL_PROCESSING : Result.ALLOWED;
    }
}