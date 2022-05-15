package com.ceiba.contrato.modelo.enums;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalariosMinimosAnnosTest {

    private static final String ANNO_SALARIO_MINIMO_INVALIDO = "El Salario mínimo de ese año no fue encontrado.";

    @Test
    @DisplayName("Debería devolver un salario mínimo año valido")
    void deberiaDevolverUnPaqueteDeContratoValido() {
        // arrange
        Integer annoSalarioMinimo = 2022;
        // act - assert
        assertEquals(SalariosMinimosAnnos.ANNO2022, SalariosMinimosAnnos.getByYear(annoSalarioMinimo));
    }

    @Test
    @DisplayName("Debería lanzar excepción por salario mínimo año no valido")
    void deberiaLanzarExcepcionPorPaqueteNoValido() {
        // arrange
        Integer annoSalarioMinimoAnno = 1800;
        // act - assert
        BasePrueba.assertThrows(() -> SalariosMinimosAnnos.getByYear(annoSalarioMinimoAnno),
                ExcepcionValorInvalido.class,
                ANNO_SALARIO_MINIMO_INVALIDO);
    }

}