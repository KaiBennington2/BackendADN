package com.ceiba.contrato.modelo.enums;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum SalariosMinimosAnnos {

    ANNO2020(2020, new BigDecimal(800000)),
    ANNO2021(22021, new BigDecimal(900000)),
    ANNO2022(2022, new BigDecimal(1000000));

    private static final String PREFIJO_ENUM_SALARIOS_MINIMOS_ANNOS = "ANNO";
    private static final String ANNO_SALARIO_MINIMO_INVALIDO = "El Salario minimo de ese año no fue encontrado.";

    private final Integer year;
    private final BigDecimal smmlv;

    private SalariosMinimosAnnos(Integer year, BigDecimal smmlv) {
        this.year = year;
        this.smmlv = smmlv;
    }

    public static SalariosMinimosAnnos getByYear(Integer anno) {
        try {
            return SalariosMinimosAnnos.valueOf(PREFIJO_ENUM_SALARIOS_MINIMOS_ANNOS + anno);
        } catch (IllegalArgumentException e) {
            throw new ExcepcionValorInvalido(ANNO_SALARIO_MINIMO_INVALIDO);
        }
    }
}
