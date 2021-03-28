package com.rnd.corp.springpocketapi.service.mapper;

import com.rnd.corp.springpocketapi.domain.finance.Finance;
import com.rnd.corp.springpocketapi.service.dto.finance.FinanceExposedDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FinanceMapper {
    FinanceExposedDTO toExposedDTO(Finance finance);
}
