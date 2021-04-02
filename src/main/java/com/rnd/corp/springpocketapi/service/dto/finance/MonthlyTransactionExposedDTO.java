package com.rnd.corp.springpocketapi.service.dto.finance;

import com.rnd.corp.springpocketapi.domain.MonthlyTransactionId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyTransactionExposedDTO {

    private MonthlyTransactionId id;
    private float balance;
    private float income;
    private float outcome;
}
