package org.banking.pet.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankOperationRequest {
    private BigDecimal amount;
}
