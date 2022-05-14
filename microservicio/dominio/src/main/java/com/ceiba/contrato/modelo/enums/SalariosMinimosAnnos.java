package com.ceiba.contrato.modelo.enums;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum SalariosMinimosAnnos {

    ANNO2020(2020, new BigDecimal(800000)),
    ANNO2021(2021, new BigDecimal(900000)),
    ANNO2022(2022, new BigDecimal(1000000));

    private static final String PREFIJO_ENUM_SALARIOS_MINIMOS_ANNOS = "ANNO";
    private static final String ANNO_SALARIO_MINIMO_INVALIDO = "El Salario mínimo de ese año no fue encontrado.";

    private final Integer anno;
    private final BigDecimal salarioMinimo;

    private SalariosMinimosAnnos(Integer anno, BigDecimal salarioMinimo) {
        this.anno = anno;
        this.salarioMinimo = salarioMinimo;
    }

    public static SalariosMinimosAnnos getByYear(Integer anno) {
        try {
            return SalariosMinimosAnnos.valueOf(PREFIJO_ENUM_SALARIOS_MINIMOS_ANNOS + anno);
        } catch (IllegalArgumentException e) {
            throw new ExcepcionValorInvalido(ANNO_SALARIO_MINIMO_INVALIDO);
        }
    }
}
