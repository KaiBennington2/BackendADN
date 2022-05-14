package com.ceiba.contrato.modelo.enums;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum PaquetesContratos {

    BASICO("BASICO", 30, 48, new BigDecimal(75)),
    COMPACTO("COMPACTO", 60, 36, new BigDecimal(50)),
    PREMIUM("PREMIUM", 90, 24, new BigDecimal(25));

    private static final String PAQUETE_CONTRATO_INVALIDO = "El paquete de contrato es inválido.";

    private final String nombre;
    private final Integer frecuenciaPagoEnDias;
    private final Integer tiempoPaqueteEnMeses;
    private final BigDecimal porcentajeBaseAplicado;

    private PaquetesContratos(
            String nombre,
            Integer frecuenciaPagoEnDias,
            Integer tiempoPaqueteEnMeses,
            BigDecimal porcentajeBaseAplicado
    ) {
        this.nombre = nombre;
        this.frecuenciaPagoEnDias = frecuenciaPagoEnDias;
        this.tiempoPaqueteEnMeses = tiempoPaqueteEnMeses;
        this.porcentajeBaseAplicado = porcentajeBaseAplicado;
    }

    public static PaquetesContratos getByName(String paqueteContrato) {
        try {
            return PaquetesContratos.valueOf(paqueteContrato.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ExcepcionValorInvalido(PAQUETE_CONTRATO_INVALIDO);
        }
    }

}


