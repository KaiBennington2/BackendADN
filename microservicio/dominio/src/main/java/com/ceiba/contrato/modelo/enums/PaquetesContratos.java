package com.ceiba.contrato.modelo.enums;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum PaquetesContratos {


    BASIC("BASIC", 30, 48, new BigDecimal(75)),
    COMPACT("COMPACT", 60, 36, new BigDecimal(50)),
    PREMIUM("PREMIUM", 90, 24, new BigDecimal(25));

    private static final String PAQUETE_CONTRATO_INVALIDO = "El paquete de contrato es inválido.";

    private final String name;
    private final Integer frequencyDayPayment;
    private final Integer minPacketTime;
    private final BigDecimal basePorcentage;

    private PaquetesContratos(String name, Integer frequencyDayPayment, Integer minPacketTime, BigDecimal basePorcentage) {
        this.name = name;
        this.frequencyDayPayment = frequencyDayPayment;
        this.minPacketTime = minPacketTime;
        this.basePorcentage = basePorcentage;
    }

    public static PaquetesContratos getByName(String packet) {
        try {
            return PaquetesContratos.valueOf(packet.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ExcepcionValorInvalido(PAQUETE_CONTRATO_INVALIDO);
        }
    }

}


