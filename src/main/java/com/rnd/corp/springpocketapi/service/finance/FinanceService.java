package com.rnd.corp.springpocketapi.service.finance;

import java.util.Optional;

import com.rnd.corp.springpocketapi.domain.finance.Finance;
import com.rnd.corp.springpocketapi.exception.ResourceNotFoundException;
import com.rnd.corp.springpocketapi.exception.UnauthorizedExceptionHandler;
import com.rnd.corp.springpocketapi.repository.finance.FinanceRepository;
import com.rnd.corp.springpocketapi.service.dto.finance.FinanceExposedDTO;
import com.rnd.corp.springpocketapi.service.mapper.FinanceMapper;
import com.rnd.corp.springpocketapi.utils.UsersServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final FinanceRepository financeRepository;
    private final FinanceMapper mapper;

    /**
     * Retrieve user's finance values
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
}
