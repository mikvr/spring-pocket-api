package com.rnd.corp.springpocketapi.service.finance;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

import com.rnd.corp.springpocketapi.domain.MonthlyTransactionId;
import com.rnd.corp.springpocketapi.domain.finance.Finance;
import com.rnd.corp.springpocketapi.domain.finance.MonthlyTransaction;
import com.rnd.corp.springpocketapi.exception.ResourceNotFoundException;
import com.rnd.corp.springpocketapi.exception.UnauthorizedExceptionHandler;
import com.rnd.corp.springpocketapi.repository.finance.FinanceRepository;
import com.rnd.corp.springpocketapi.repository.finance.MonthlyTransactionRepository;
import com.rnd.corp.springpocketapi.service.dto.finance.FinanceExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.MonthlyTransactionExposedDTO;
import com.rnd.corp.springpocketapi.service.mapper.FinanceMapper;
import com.rnd.corp.springpocketapi.utils.UsersServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinanceGetterService {
    private final FinanceRepository financeRepository;
    private final MonthlyTransactionRepository monthlyTransactionRepository;
    private final FinanceMapper mapper;

    /**
     * Retrieve user's finance values
     *
     * @param login user's login
     * @return finance data
     */
    public ResponseEntity<FinanceExposedDTO> getFinance(final String login) {
        if (UsersServiceHelper.checkUserOrigin(login)) {
            final Optional<Finance> finance = this.financeRepository.findByUserId(login);
            if (finance.isPresent()) {
                return ResponseEntity.ok(this.mapper.toExposedDTO(finance.get()));
            }
            throw new ResourceNotFoundException();
        }
        throw new UnauthorizedExceptionHandler();
    }

    /**
     * Search user's transaction by month
     *
     * @param login     user's login
     * @param date      associated date
     * @param financeId associated finance id
     * @return transaction of the given month
     */
    public ResponseEntity<MonthlyTransactionExposedDTO> getMonthlyTransaction(final String login, final Date date,
        final int financeId) {

        if (UsersServiceHelper.checkUserOrigin(login)) {

            final Instant associatedDate = Instant.ofEpochMilli(date.getTime())
                                                  .atZone(ZoneId.systemDefault())
                                                  .toLocalDateTime()
                                                  .withDayOfMonth(2)
                                                  .toInstant(ZoneOffset.UTC);

            final Optional<MonthlyTransaction> transaction =
                this.monthlyTransactionRepository.findById(new MonthlyTransactionId(associatedDate, financeId));

            if (transaction.isPresent()) {
                return ResponseEntity.ok(this.mapper.toExposedMTransactionDTO(transaction.get()));
            }
            throw new ResourceNotFoundException();
        }
        throw new UnauthorizedExceptionHandler();
    }
}
