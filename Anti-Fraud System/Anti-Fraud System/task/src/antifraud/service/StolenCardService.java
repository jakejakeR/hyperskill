package antifraud.service;

import antifraud.model.card.StolenCard;
import antifraud.repository.StolenCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
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

    @Transactional
    public Optional<Map<String, String>> deleteStolenCard(String number) {
        if (repository.deleteByNumber(number) == 0) {
            return Optional.empty();
        }
        return Optional.of(
                Map.of("status", String.format("Card %s successfully removed!", number))
        );
    }

    public List<StolenCard> listStolenCards() {
        return repository
                .findAll(Sort
                        .sort(StolenCard.class)
                        .by(StolenCard::getId)
                        .ascending());
    }
}
