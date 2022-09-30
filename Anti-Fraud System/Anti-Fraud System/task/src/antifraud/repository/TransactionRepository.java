package antifraud.repository;

import antifraud.model.transaction.TransactionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionRequest, Long> {
}
