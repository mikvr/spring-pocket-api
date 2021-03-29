package com.rnd.corp.springpocketapi.controller;

import java.util.Date;

import com.rnd.corp.springpocketapi.service.dto.finance.FinanceExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.MonthlyTransactionExposedDTO;
import com.rnd.corp.springpocketapi.service.finance.FinanceGetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FinanceController {
    private final FinanceGetterService financeGetterService;

    @GetMapping("/{login}/finance")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<FinanceExposedDTO> getFinance(@PathVariable("login") String login) {
        return this.financeGetterService.getFinance(login);
    }

    @GetMapping("/{login}/finance/month")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<MonthlyTransactionExposedDTO> getMonthFinance(
        @PathVariable("login") String login,
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM") Date date,
        @RequestParam("financeId") int financeId) {

        return this.financeGetterService.getMonthlyTransaction(login, date, financeId);
    }
}
