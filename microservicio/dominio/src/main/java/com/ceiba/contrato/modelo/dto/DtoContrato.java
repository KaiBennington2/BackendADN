package com.ceiba.contrato.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoContrato {
    private Long id;
    private String nitCliente;
    private Integer tiempoContratoMeses;
    private String tipoMoneda;
    private BigDecimal trmAplicada;
    private String paquete;
    private LocalDate fechaInstalacion;
    private LocalDate fechaCorte;
    private LocalDate fechaCorteAnterior;
}
