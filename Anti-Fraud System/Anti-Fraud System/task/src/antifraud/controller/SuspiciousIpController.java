package antifraud.controller;

import antifraud.model.ip.SuspiciousIp;
import antifraud.service.SuspiciousIpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/antifraud/suspicious-ip")
@AllArgsConstructor
public class SuspiciousIpController {

    SuspiciousIpService service;

    @PostMapping
    @PreAuthorize("hasAuthority('SUPPORT')")
    @ResponseStatus(HttpStatus.CREATED)
    public SuspiciousIp register(@Valid @RequestBody SuspiciousIp ip) {
        log.info("IP: {}", ip);
        return service.register(ip)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
    }
}
