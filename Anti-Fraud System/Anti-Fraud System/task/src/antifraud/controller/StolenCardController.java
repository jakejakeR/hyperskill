package antifraud.controller;

import antifraud.model.card.StolenCard;
import antifraud.service.StolenCardService;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@PreAuthorize("hasAuthority('SUPPORT')")
@RequestMapping("/api/antifraud/stolencard")
@AllArgsConstructor
public class StolenCardController {

    StolenCardService service;

    @PostMapping
    public StolenCard register(@Valid @RequestBody StolenCard card) {
        return service.register(card)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT));
    }

    @DeleteMapping({"/{number}", ""})
    public Map<String, String> delete(@PathVariable(required = false) @CreditCardNumber String number) {
        if (number == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return service.deleteStolenCard(number)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<StolenCard> list() {
        return service.listStolenCards();
    }
}
