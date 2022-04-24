package com.ceiba.contrato.comando.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoContrato {

    private Long id;
    private String nitCliente;
    private String tipoMoneda;
    private BigDecimal trmAplicada;
    private String paquete;
    private LocalDate fechaInstalacion;

}
