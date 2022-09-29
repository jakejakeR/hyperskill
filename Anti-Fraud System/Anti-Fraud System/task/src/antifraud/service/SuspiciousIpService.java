package antifraud.service;

import antifraud.model.ip.SuspiciousIp;
import antifraud.repository.SuspiciousIpRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SuspiciousIpService {
    SuspiciousIpRepository repository;

    @Transactional
    public Optional<SuspiciousIp> register(SuspiciousIp suspiciousIp) {
        if (repository.existsByIp(suspiciousIp.getIp())) {
            return Optional.empty();
        }
        return Optional.of(repository.save(suspiciousIp));
    }

    @Transactional
    public Optional<Map<String, String>> deleteSuspiciousIp(String ip) {
        if (repository.deleteByIp(ip) == 0) {
            return Optional.empty();
        }
        return Optional.of(
                Map.of("status", String.format("IP %s successfully removed!", ip))
        );
    }

    public List<SuspiciousIp> listSuspiciousIps() {
        return repository
                .findAll(Sort
                        .sort(SuspiciousIp.class)
                        .by(SuspiciousIp::getId)
                        .ascending());
    }
}
