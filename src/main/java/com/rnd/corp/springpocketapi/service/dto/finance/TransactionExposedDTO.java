package com.rnd.corp.springpocketapi.service.dto.finance;

import java.util.Date;

import com.rnd.corp.springpocketapi.service.dto.LabelExposedDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionExposedDTO {

    private int id;
    private LabelExposedDTO label;
    private float amount;
    private int qty;
    private float unitPrice;
    private Date date;

}
