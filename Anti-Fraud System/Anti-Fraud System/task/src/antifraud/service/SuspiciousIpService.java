package antifraud.service;

import antifraud.model.ip.SuspiciousIp;
import antifraud.repository.SuspiciousIpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
