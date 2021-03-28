package com.rnd.corp.springpocketapi.service.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinanceExposedDTO {

    private int id;
    private float balance;
    private float income;
    private float outcome;
}
