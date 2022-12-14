package antifraud.controller;

import antifraud.model.transaction.TransactionRequest;
import antifraud.model.transaction.TransactionResponse;
import antifraud.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAuthority('MERCHANT')")
@RequestMapping("/api/antifraud/transaction")
public class TransactionController {
    public final TransactionService transactionService;

    @PostMapping
    TransactionResponse purchase(@Valid @RequestBody TransactionRequest request) {
        return transactionService.processAbstract(request);
    }
}