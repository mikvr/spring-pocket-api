package com.rnd.corp.springpocketapi.service.finance;

import java.time.Instant;
import java.util.Optional;

import com.rnd.corp.springpocketapi.domain.MonthlyTransactionId;
import com.rnd.corp.springpocketapi.domain.finance.Finance;
import com.rnd.corp.springpocketapi.domain.finance.MonthlyTransaction;
import com.rnd.corp.springpocketapi.domain.finance.Transaction;
import com.rnd.corp.springpocketapi.exception.UnauthorizedExceptionHandler;
import com.rnd.corp.springpocketapi.repository.LabelRepository;
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
    private final LabelRepository labelRepository;

    private final FinanceMapper mapper;

    /**
     * Add a Transaction to associated finance ID
     * If there is no monthly Transaction associated, it will be generated
     * Else the monthly Transaction associated will be updated
     *
     * @param login       user's login
     * @param transaction transaction to add
     * @param financeId   associated financeId
     * @return saved transaction
     */
    public ResponseEntity<TransactionExposedDTO> addTransaction(final String login,
        final TransactionFormDTO transaction, final int financeId) {

        if (UsersServiceHelper.checkUserOrigin(login)) {
            MonthlyTransaction mTransaction;
            final Instant associatedMonthDate = FinanceServiceHelper.getAssociatedMonthDate(transaction.getDate());
            final MonthlyTransactionId id = new MonthlyTransactionId(associatedMonthDate, financeId);

            Optional<MonthlyTransaction> associatedMonth = this.monthlyTransactionRepository.findById(id);
            if (associatedMonth.isPresent()) {
                mTransaction = associatedMonth.get();
                mTransaction.updateState(transaction.getAmount());

            } else {
                mTransaction = new MonthlyTransaction(id, transaction.getAmount());
            }

            final Transaction entity = this.mapper.toEntity(transaction);
            entity.updateAssociatedMonthlyTransaction(mTransaction);

            if (!this.labelRepository.existsById(entity.getLabel().getId())) {
                entity.setLabel(this.labelRepository.saveAndFlush(entity.getLabel()));
            }
            final Finance finance = entity.getMtId().getFinance();

            final Transaction savedTransaction = this.transactionRepository.save(entity);
            return ResponseEntity.ok(this.mapper.toExposedTransactionDTO(savedTransaction));

        }
        throw new UnauthorizedExceptionHandler();
    }

}
