package com.rnd.corp.springpocketapi.service.mapper;

import com.rnd.corp.springpocketapi.domain.finance.Finance;
import com.rnd.corp.springpocketapi.domain.finance.MonthlyTransaction;
import com.rnd.corp.springpocketapi.service.dto.finance.FinanceExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.MonthlyTransactionExposedDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FinanceMapper {
    FinanceExposedDTO toExposedDTO(Finance finance);
    MonthlyTransactionExposedDTO toExposedMTransactionDTO(MonthlyTransaction entity);
}
