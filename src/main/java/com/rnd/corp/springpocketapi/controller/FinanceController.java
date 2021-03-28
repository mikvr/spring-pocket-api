package com.rnd.corp.springpocketapi.controller;

import com.rnd.corp.springpocketapi.service.dto.finance.FinanceExposedDTO;
import com.rnd.corp.springpocketapi.service.finance.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FinanceController {
    private final FinanceService financeService;

    @GetMapping("/{login}/finance")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<FinanceExposedDTO> getFinance(@PathVariable("login") String login) {
        return this.financeService.getFinance(login);
    }
}
