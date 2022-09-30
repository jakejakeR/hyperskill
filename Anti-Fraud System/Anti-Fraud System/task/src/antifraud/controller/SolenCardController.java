package antifraud.controller;

import antifraud.model.card.StolenCard;
import antifraud.service.StolenCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasAuthority('SUPPORT')")
@RequestMapping("/api/antifraud/stolencard")
@AllArgsConstructor
public class SolenCardController {

    StolenCardService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StolenCard register(@Valid @RequestBody StolenCard card) {
        return service.register(card)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
    }

    @DeleteMapping("/{number}")
    public Map<String, String> delete(@PathVariable("number") String number) {
        return service.deleteStolenCard(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/")
    public void delete() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @GetMapping
    public List<StolenCard> list() {
        return service.listStolenCards();
    }
}
