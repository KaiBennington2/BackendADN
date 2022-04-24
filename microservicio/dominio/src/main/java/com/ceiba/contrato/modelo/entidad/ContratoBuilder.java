package com.ceiba.contrato.modelo.entidad;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ContratoBuilder {

    private Long id;
    private String nitCustomer;
    private Integer contractTime;
    private String coinType;
    private BigDecimal trmApplied;
    private String packet;
    private LocalDate installDate;
    private LocalDate cutOffDate;
    private LocalDate cutOffBeforeDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ContratoBuilder(Contrato contrato) {
        this.id = contrato.getId();
        this.nitCustomer = contrato.getNitCustomer();
        this.coinType = contrato.getCoinType();
        this.trmApplied = contrato.getTrmApplied();
        this.packet = contrato.getPacket();
        this.installDate = contrato.getInstallDate();
        this.cutOffDate = contrato.getCutOffDate();
        this.createdDate = contrato.getCreatedDate();
        this.modifiedDate = contrato.getModifiedDate();
    }

    public ContratoBuilder setContractTime(Integer contractTime) {
        this.contractTime = contractTime;
        return this;
    }

    public ContratoBuilder setCutOffDate(LocalDate cutOffDate) {
        this.cutOffDate = cutOffDate;
        return this;
    }

    public ContratoBuilder setCutOffBeforeDate(LocalDate cutOffBeforeDate) {
        this.cutOffBeforeDate = cutOffBeforeDate;
        return this;
    }

    public ContratoBuilder setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public ContratoBuilder setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public Contrato build() {
        return new Contrato(
                id,
                nitCustomer,
                contractTime,
                coinType,
                trmApplied,
                packet,
                installDate,
                cutOffDate,
                cutOffBeforeDate);
    }
}
