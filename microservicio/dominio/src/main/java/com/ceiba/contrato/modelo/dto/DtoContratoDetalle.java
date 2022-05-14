package com.ceiba.contrato.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoContratoDetalle {
    private Long id;
    private String nitCliente;
    private Integer tiempoContratoMeses;
    private String tipoMoneda;
    private BigDecimal trmAplicada;
    private String paqueteContrato;
    private LocalDate fechaInstalacion;
    private LocalDate fechaCorte;
    private LocalDate fechaCorteAnterior;
    private BigDecimal valorContrato;
}
