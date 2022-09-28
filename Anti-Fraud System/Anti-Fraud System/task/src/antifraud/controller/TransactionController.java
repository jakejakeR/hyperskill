package antifraud.controller;

import antifraud.model.transaction.TransactionRequest;
import antifraud.model.transaction.TransactionResponse;
import antifraud.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class TransactionController {

    public final TransactionService transactionService;

    @PreAuthorize("hasAuthority('MERCHANT')")
    @PostMapping("/api/antifraud/transaction")
    TransactionResponse purchase(@Valid @RequestBody TransactionRequest request) {
        return TransactionResponse.of(
            transactionService.process(request.getAmount())
        );
    }
}
