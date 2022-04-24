package com.ceiba.contrato.builders;

import com.ceiba.contrato.modelo.dto.DtoContrato;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalDate.now;

public class DtoContratoBuilder {

    private Long id;
    private String nitCustomer;
    private Integer contractTime;
    private String coinType;
    private BigDecimal trmApplied;
    private String packet;
    private LocalDate installDate;
    private LocalDate cutOffDate;
    private LocalDate cutOffBeforeDate;

    public DtoContratoBuilder() {
        nitCustomer = "1234567";
        contractTime = 24;
        coinType = "USD";
        trmApplied = new BigDecimal(3702.86);
        packet = "PREMIUM";
        installDate = now();
    }

    public DtoContratoBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoContratoBuilder conNit(String nitCliente) {
        this.nitCustomer = nitCliente;
        return this;
    }

    public DtoContratoBuilder conTiempoContrato(Integer cantMeses) {
        this.contractTime = cantMeses;
        return this;
    }

    public DtoContratoBuilder conTipoMoneda(String tipoMoneda) {
        this.coinType = tipoMoneda;
        return this;
    }

    public DtoContratoBuilder conTrmAplicada(BigDecimal trmAplicada) {
        this.trmApplied = trmAplicada;
        return this;
    }

    public DtoContratoBuilder conPaquete(String paquete) {
        this.packet = paquete;
        return this;
    }

    public DtoContratoBuilder conFechaInstalacion(LocalDate fechaInstalacion) {
        this.installDate = fechaInstalacion;
        return this;
    }

    public DtoContrato build() {
        return new DtoContrato(
                id,
                nitCustomer,
                contractTime,
                coinType,
                trmApplied,
                packet,
                installDate,
                cutOffDate,
                cutOffBeforeDate
        );
    }
}
