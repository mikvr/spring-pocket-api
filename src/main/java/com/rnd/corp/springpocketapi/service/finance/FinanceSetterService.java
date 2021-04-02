package com.rnd.corp.springpocketapi.service.finance;

import java.time.Instant;
import java.util.Optional;

import com.rnd.corp.springpocketapi.domain.MonthlyTransactionId;
import com.rnd.corp.springpocketapi.domain.finance.MonthlyTransaction;
import com.rnd.corp.springpocketapi.domain.finance.Transaction;
import com.rnd.corp.springpocketapi.exception.UnauthorizedExceptionHandler;
import com.rnd.corp.springpocketapi.repository.finance.MonthlyTransactionRepository;
import com.rnd.corp.springpocketapi.repository.finance.TransactionRepository;
import com.rnd.corp.springpocketapi.service.dto.finance.TransactionExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.TransactionFormDTO;
import com.rnd.corp.springpocketapi.service.mapper.FinanceMapper;
import com.rnd.corp.springpocketapi.utils.FinanceServiceHelper;
import com.rnd.corp.springpocketapi.utils.UsersServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinanceSetterService {

    private final MonthlyTransactionRepository monthlyTransactionRepository;
    private final TransactionRepository transactionRepository;

    private final FinanceMapper mapper;

    public ResponseEntity<TransactionExposedDTO> addTransaction(final String login,
        final TransactionFormDTO transaction, final int financeId) {

        if (UsersServiceHelper.checkUserOrigin(login)) {
            final Instant associatedMonthDate = FinanceServiceHelper.getAssociatedMonthDate(transaction.getDate());
            final MonthlyTransactionId id = new MonthlyTransactionId(associatedMonthDate, financeId);
            Optional<MonthlyTransaction> associatedMonth = this.monthlyTransactionRepository.findById(id);
            if (associatedMonth.isPresent()) {
                transaction.setMtId(associatedMonth.get());
            } else {
                final MonthlyTransaction newMTransaction = new MonthlyTransaction(id, transaction.getAmount());
                final MonthlyTransaction saved = this.monthlyTransactionRepository.save(newMTransaction);
                transaction.setMtId(saved);
            }
            final Transaction entity = this.mapper.toEntity(transaction);
            final Transaction savedTransaction = this.transactionRepository.save(entity);
            return ResponseEntity.ok(this.mapper.toExposedTransactionDTO(savedTransaction));
            // TODO : maj finance et mois associe
        }
        throw new UnauthorizedExceptionHandler();
    }

}
