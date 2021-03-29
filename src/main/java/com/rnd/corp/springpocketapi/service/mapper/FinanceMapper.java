package com.rnd.corp.springpocketapi.service.mapper;

import com.rnd.corp.springpocketapi.domain.Label;
import com.rnd.corp.springpocketapi.domain.finance.Finance;
import com.rnd.corp.springpocketapi.domain.finance.MonthlyTransaction;
import com.rnd.corp.springpocketapi.domain.finance.Transaction;
import com.rnd.corp.springpocketapi.service.dto.LabelExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.FinanceExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.MonthlyTransactionExposedDTO;
import com.rnd.corp.springpocketapi.service.dto.finance.TransactionExposedDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FinanceMapper {
    FinanceExposedDTO toExposedDTO(Finance finance);
    MonthlyTransactionExposedDTO toExposedMTransactionDTO(MonthlyTransaction entity);
    LabelExposedDTO toExposedLabelDTO(Label label);
    TransactionExposedDTO toExposedTransactionDTO(Transaction transaction);
}
