package com.ceiba.contrato.builders;

import com.ceiba.contrato.comando.dto.ComandoContrato;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ComandoContratoTestDataBuilder {

    private Long id;
    private String nitCliente;
    private String tipoMoneda;
    private BigDecimal trmAplicada;
    private String paquete;
    private LocalDate fechaInstalacion;

    public ComandoContratoTestDataBuilder() {
        nitCliente = "12345";
        tipoMoneda = "USD";
        trmAplicada = new BigDecimal("3702.86");
        paquete = "BASICO";
        fechaInstalacion = LocalDate.now();

    }

    public ComandoContratoTestDataBuilder conNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
        return this;
    }

    public ComandoContratoTestDataBuilder conTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
        return this;
    }

    public ComandoContratoTestDataBuilder conTrmAplicada(BigDecimal trmAplicada) {
        this.trmAplicada = trmAplicada;
        return this;
    }

    public ComandoContratoTestDataBuilder conPaquete(String paquete) {
        this.paquete = paquete;
        return this;
    }

    public ComandoContratoTestDataBuilder conFechaInstalacion(LocalDate fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
        return this;
    }


    public ComandoContrato build() {
        return new ComandoContrato(
                id,
                nitCliente,
                tipoMoneda,
                trmAplicada,
                paquete,
                fechaInstalacion
        );
    }
}
