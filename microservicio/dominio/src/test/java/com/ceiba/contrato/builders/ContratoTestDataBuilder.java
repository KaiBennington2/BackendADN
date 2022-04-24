package com.ceiba.contrato.builders;

import com.ceiba.contrato.modelo.entidad.Contrato;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContratoTestDataBuilder {

    private Long id;
    private String nitCustomer;
    private String coinType;
    private BigDecimal trmApplied;
    private String packet;
    private LocalDate installDate;

    public ContratoTestDataBuilder() {
        nitCustomer = "1234567";
        coinType = "COP";
        trmApplied = new BigDecimal("3702.86");
        packet = "BASIC";
        installDate = LocalDate.now();

    }

    public ContratoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ContratoTestDataBuilder conNit(String nitCliente) {
        this.nitCustomer = nitCliente;
        return this;
    }

    public ContratoTestDataBuilder conTipoMoneda(String tipoMoneda) {
        this.coinType = tipoMoneda;
        return this;
    }

    public ContratoTestDataBuilder conTrmAplicada(BigDecimal trmAplicada) {
        this.trmApplied = trmAplicada;
        return this;
    }

    public ContratoTestDataBuilder conPaquete(String paquete) {
        this.packet = paquete;
        return this;
    }

    public ContratoTestDataBuilder conFechaInstalacion(LocalDate fechaInstalacion) {
        this.installDate = fechaInstalacion;
        return this;
    }

    public Contrato build() {
        return new Contrato(
                id,
                nitCustomer,
                coinType,
                trmApplied,
                packet,
                installDate);
    }

}
