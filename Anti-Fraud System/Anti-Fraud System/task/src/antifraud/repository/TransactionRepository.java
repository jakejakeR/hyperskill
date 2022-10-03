package antifraud.repository;

import antifraud.model.transaction.TransactionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionRequest, Long> {
    List<TransactionRequest> findByNumberAndDateBetween(String number, LocalDateTime dateStart, LocalDateTime dateEnd);
}
