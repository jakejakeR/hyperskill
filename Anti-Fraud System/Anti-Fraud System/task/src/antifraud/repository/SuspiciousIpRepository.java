package antifraud.repository;

import antifraud.model.ip.SuspiciousIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuspiciousIpRepository extends JpaRepository<SuspiciousIp, Long> {
    boolean existsByIp(String ip);
}
