package com.rnd.corp.springpocketapi.controller;

import java.util.Date;
import java.util.List;

import com.rnd.corp.springpocketapi.service.dto.finance.FinanceExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.MonthlyTransactionExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.TransactionExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.TransactionFormDTO;
import com.rnd.corp.springpocketapi.service.finance.FinanceGetterService;
import com.rnd.corp.springpocketapi.service.finance.FinanceSetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class FinanceController {
    private final FinanceGetterService financeGetterService;
    private final FinanceSetterService financeSetterService;

    @GetMapping("/{login}/finance")
    public ResponseEntity<FinanceExposedDTO> getFinanceById(@PathVariable("login") String login) {
        return this.financeGetterService.getFinance(login);
    }

    @GetMapping("/{login}/finance/month")
    public ResponseEntity<MonthlyTransactionExposedDTO> getMonthFinanceById(
        @PathVariable("login") String login,
        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM") Date date,
        @RequestParam("financeId") int financeId) {
        return this.financeGetterService.getMonthlyTransaction(login, date, financeId);
    }

    @GetMapping("/{login}/finance/transaction")
    public ResponseEntity<TransactionExposedDTO> getTransactionById(@PathVariable("login") String login,
        @RequestParam("financeId") int id) {
        return this.financeGetterService.getTransactionById(login, id);
    }

    @GetMapping("/{login}/finance/transaction/all/month")
    public ResponseEntity<List<TransactionExposedDTO>> getAllTransactionByMonth(
        @PathVariable("login") String login,
        @RequestParam("monthId") Date monthDate,
        @RequestParam("financeId") int financeId) {
        return this.financeGetterService.getAllTransactionByMonth(login, monthDate, financeId);
    }

    @PostMapping("/{login}/finance/{financeId}/transaction")
    public ResponseEntity<TransactionExposedDTO> addTransaction(@PathVariable("login") String login,
        @PathVariable("financeId") int financeId, @RequestBody TransactionFormDTO transaction) {
        return this.financeSetterService.addTransaction(login, transaction, financeId);
    }
}
