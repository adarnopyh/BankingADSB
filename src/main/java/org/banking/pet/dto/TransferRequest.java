package org.banking.pet.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private Long targetUserId;
    private BigDecimal amount;
}
