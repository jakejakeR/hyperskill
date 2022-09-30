package antifraud.service;

import antifraud.model.card.StolenCard;
import antifraud.repository.StolenCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StolenCardService {

    StolenCardRepository repository;

    @Transactional
    public Optional<StolenCard> register(StolenCard card) {
        if (repository.existsByNumber(card.getNumber())) {
            return Optional.empty();
        }
        return Optional.of(repository.save(card));
    }
}
