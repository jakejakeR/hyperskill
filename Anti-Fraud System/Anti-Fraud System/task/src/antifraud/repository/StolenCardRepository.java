package antifraud.repository;

import antifraud.model.card.StolenCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StolenCardRepository extends JpaRepository<StolenCard, Long> {
    boolean existsByNumber(String number);

    long deleteByNumber(String number);
}
