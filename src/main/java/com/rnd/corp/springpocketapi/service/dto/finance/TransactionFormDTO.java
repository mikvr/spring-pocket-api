package com.rnd.corp.springpocketapi.service.dto.finance;

import java.util.Date;

import com.rnd.corp.springpocketapi.domain.Label;
import com.rnd.corp.springpocketapi.domain.finance.MonthlyTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFormDTO {

    private int id;
    private Label label;
    private float amount;
    private int qty;
    private float unitPrice;
    private Date date;
    private MonthlyTransaction mtId = null;
}
