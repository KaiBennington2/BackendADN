package com.ceiba.contrato.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoContrato {
    private Long id;
    private String nitCustomer;
    private Integer contractTime;
    private String coinType;
    private BigDecimal trmApplied;
    private String packet;
    private LocalDate installDate;
    private LocalDate cutOffDate;
    private LocalDate cutOffBeforeDate;
}
