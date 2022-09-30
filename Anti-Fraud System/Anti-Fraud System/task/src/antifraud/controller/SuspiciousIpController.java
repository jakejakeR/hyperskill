package antifraud.controller;

import antifraud.model.ip.SuspiciousIp;
import antifraud.service.SuspiciousIpService;
import antifraud.validation.Ipv4;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@PreAuthorize("hasAuthority('SUPPORT')")
@RequestMapping("/api/antifraud/suspicious-ip")
@AllArgsConstructor
public class SuspiciousIpController {

    SuspiciousIpService service;

    @PostMapping
    public SuspiciousIp register(@Valid @RequestBody SuspiciousIp ip) {
        return service.register(ip)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
    }

    @DeleteMapping("/{ip}")
    public Map<String, String> delete(@PathVariable("ip") @Ipv4 String ip) {
        return service.deleteSuspiciousIp(ip)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/")
    public void delete() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping
    public List<SuspiciousIp> list() {
        return service.listSuspiciousIps();
    }
}
